package com.tqz.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * <p>基于内存的认证服务器配置
 *
 * @author tianqingzhao
 * @since 2022/8/5 15:52
 * @deprecated 使用 {@link InDbAuthorizationConfig} 数据库模式
 */
//@Configuration
//@EnableAuthorizationServer
@Deprecated
public class InMemoryAuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    /**
     * 资源服务器校验token需要带上自己的appid、app_secret、
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");
    }
    
    /**
     * 授权服务器需要配置哪些app向它索取令牌
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("protal-app") // 客户端标识，相当于appid
                .secret(passwordEncoder.encode("protal-app")) // 加密密钥，相当于app_secret
                .authorizedGrantTypes("password", "authorization_code") // 密码模式、授权码模式
                .scopes("read") // 读权限
                .accessTokenValiditySeconds(3600) // token过期时间
                .resourceIds("order-service", "product-service") // product-service服务可以访问哪些服务
                .redirectUris("https://www.baidu.com") // 授权码模式使用的回调地址
                .and().withClient("order-app").secret(passwordEncoder.encode("order-service"))
                .authorizedGrantTypes("password").scopes("read").accessTokenValiditySeconds(3600)
                .resourceIds("order-service").and().withClient("product-app")
                .secret(passwordEncoder.encode("product-app")).authorizedGrantTypes("password").scopes("read")
                .accessTokenValiditySeconds(3600).resourceIds("product-service");
    }
    
    /**
     * 认证服务器用来验证用户信息，以便知道访问哪个资源服务器。 用来校验用户输入的用户名、密码是否正确
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }
}
