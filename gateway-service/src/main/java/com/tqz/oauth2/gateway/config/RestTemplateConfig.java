package com.tqz.oauth2.gateway.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * <p>使resttemplate支持负载均衡
 *
 * @author tianqingzhao
 * @since 2022/8/27 17:06
 */
@Configuration
public class RestTemplateConfig {
    
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
