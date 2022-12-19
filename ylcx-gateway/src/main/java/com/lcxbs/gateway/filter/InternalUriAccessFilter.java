package com.lcxbs.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;
import reactor.core.publisher.Mono;


/**
 * Description
 * 内部URI访问过滤器
 */
@Component
public class InternalUriAccessFilter extends AbstractGatewayFilterFactory<Object> {

    @Override
    public GatewayFilter apply(Object config)
    {
        return (exchange, chain) -> {

            String url = exchange.getRequest().getURI().getPath();
            if (PatternMatchUtils.simpleMatch("*-anon/internal*", url))//是否执行该过滤器，此处为true，说明需要过滤
            {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                DataBuffer dataBuffer = exchange.getResponse().bufferFactory().wrap("请求地址不允许访问".getBytes());
                return exchange.getResponse().writeWith(Mono.just(dataBuffer));
            }

            return chain.filter(exchange);
        };
    }
}
