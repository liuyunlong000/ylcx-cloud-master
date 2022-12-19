package com.lcxbs.gateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈网关服务启动类〉
 */
@RestController
@SpringBootApplication
@EnableDiscoveryClient // 允许注册nacos
@ComponentScan(value = {"com.lcxbs"})
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Autowired
    private ConfigInfo configInfo;

    @RequestMapping("/value")
    public String getValue() {
        return configInfo.getConfig();
    }

}