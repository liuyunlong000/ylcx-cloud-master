package com.lcxbs.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.lcxbs.gateway.constant.NeedDecodeConstant;
import com.lcxbs.gateway.utils.RSAConstant;
import com.lcxbs.gateway.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import io.netty.buffer.ByteBufAllocator;

/**
 * 解密密码参数
 */
@Component
public class DecoderPasswordFilter extends AbstractGatewayFilterFactory<Object> {

    private final Logger log= LoggerFactory.getLogger(DecoderPasswordFilter.class);

    @Override
    public GatewayFilter apply(Object config)
    {
        return (exchange, chain) -> {
            String url = exchange.getRequest().getURI().getPath();
            String [] matchUri = NeedDecodeConstant.NEED_DECODE_URL;
            if (PatternMatchUtils.simpleMatch(matchUri, url)) {
                ServerHttpRequest req = exchange.getRequest();
                if (req.getMethodValue().equals(HttpMethod.GET.name())) {
                    String foreEndPassword = req.getQueryParams().getFirst(RSAConstant.PASSWORD);
                    if (StringUtil.isNotBlank(foreEndPassword) && foreEndPassword.length() % RSAConstant.ENCRYPTION_LENGTH == 0) {
                        List<String> arrayList = new ArrayList<>();
                        arrayList.add(getPassword(foreEndPassword));
                        req.getQueryParams().replace(RSAConstant.PASSWORD, arrayList);
                    }
                } else if (req.getMethodValue().equals(HttpMethod.POST.name()) || req.getMethodValue().equals(HttpMethod.PUT.name())) {
                    String body = resolveBodyFromRequest(req);
                    if (StringUtil.isNotBlank(body) && body.startsWith("{")) {
                        System.err.println("解密前::body> " + body);
                        JSONObject json = JSONObject.parseObject(body);
                        Boolean hasPassword = json.containsKey(RSAConstant.PASSWORD);
                        if (hasPassword) {
                            String foreEndPassword = json.get(RSAConstant.PASSWORD).toString();
                            if (foreEndPassword.length() % RSAConstant.ENCRYPTION_LENGTH == 0) {
                                json.put(RSAConstant.PASSWORD, getPassword(foreEndPassword));
                            }
                        }
                        log.info("map2json:{}", json);
                        System.err.println("解密后::body> " + json);
                        ServerHttpRequest newReq=generateNewRequest(exchange.getRequest(), json.toString());
                        return chain.filter(exchange.mutate().request(newReq).build());
                    }
                }
            }
            return chain.filter(exchange);
        };
    }

    /**
     * 解密密码
     * @param foreEndPassword 前端加密密码
     * @return 解密后的密码
     * @author DING WEI
     * @date 2020/3/5 17:08
     * @version 1.0
     */
    public String getPassword(String foreEndPassword) {
        if (foreEndPassword.length() % RSAConstant.ENCRYPTION_LENGTH == 0) {
//            String privateKey = RSAUtils.getPrivateKey();
//            try {
//                foreEndPassword = RSAUtils.decrypt(foreEndPassword, RSAUtils.getPrivateKey(privateKey));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
        return foreEndPassword;
    }


    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest)
    {
        // 获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();
        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        return bodyRef.get();
    }

    private ServerHttpRequest generateNewRequest(ServerHttpRequest request, String requestBody) {
        URI ex = UriComponentsBuilder.fromUri(request.getURI()).build(true).toUri();
        ServerHttpRequest newRequest = request.mutate().uri(ex).build();
        DataBuffer dataBuffer = stringBuffer(requestBody);
        Flux<DataBuffer> flux = Flux.just(dataBuffer);
        newRequest = new ServerHttpRequestDecorator(newRequest) {
            @Override
            public Flux<DataBuffer> getBody() {
                return flux;
            }
        };
        return newRequest;
    }

    private DataBuffer stringBuffer(String value) {
        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }
}
