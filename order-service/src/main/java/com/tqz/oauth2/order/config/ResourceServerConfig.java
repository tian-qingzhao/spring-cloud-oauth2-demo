/*
package com.tqz.oauth2.order.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

*/
/**
 * <p>资源服务器
 *
 * @author tianqingzhao
 * @since 2022/8/27 12:19
 *//*

//@Configuration
//@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    
    public ResourceServerConfig() {
        super();
    }
    
    */
/**
     * 配置资源服务器的名称，需要和授权服务器(oauth2-server)配置的保持一致
     *
     * @param resources 资源
     * @throws Exception
     *//*

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("order-service");
    }
    
    */
/**
     * 资源服务器的安全配置
     *
     * @param http
     * @throws Exception
     *//*

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/order/selectById").access("#oauth2.hasScope('read')") // 该接口只有读权限
                .and()
                .authorizeRequests()
                .antMatchers("/order/saveOrder").access("#oauth2.hasScope('write')") // 该接口只有写权限
                .and()
                .authorizeRequests()
                .antMatchers("/test").permitAll(); // 不需要令牌也能访问
    }
}
*/
