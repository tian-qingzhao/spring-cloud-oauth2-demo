/*
package com.tqz.oauth2.order.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.client.RestTemplate;

*/
/**
 * <p>资源服务器去认证服务器校验token
 *
 * @author tianqingzhao
 * @since 2022/8/27 12:29
 *//*

//@Configuration
public class WebSecurityConfig {
    
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @Bean
    public ResourceServerTokenServices resourceServerTokenServices() {
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        
        remoteTokenServices.setClientId("order-app");
        remoteTokenServices.setClientSecret("order-service");
        remoteTokenServices.setCheckTokenEndpointUrl("http://oauth2-server/oauth/check_token");
        remoteTokenServices.setRestTemplate(restTemplate());
        
        return remoteTokenServices;
    }
}
*/
