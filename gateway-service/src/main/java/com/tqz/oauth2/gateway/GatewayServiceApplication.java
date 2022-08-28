package com.tqz.oauth2.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>网关服务启动类
 *
 * @author tianqingzhao
 * @since 2022/8/27 15:10
 */
@SpringBootApplication
public class GatewayServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
}
