package com.tqz.oauth2.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * <p>基于数据库的认证服务器配置
 *
 * @author tianqingzhao
 * @since 2022/8/27 13:23
 */
@Configuration
@EnableAuthorizationServer
public class InDbAuthorizationConfig extends AuthorizationServerConfigurerAdapter {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    
    @Autowired
    private JsonSerializationStrategy jsonSerializationStrategy;
    
    @Autowired
    private DataSource dataSource;
    
    /**
     * 第三方客户端的token存储方式，对应oauth2数据库的表为：oauth_access_token
     *
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        // mysql数据库存储token
        return new JdbcTokenStore(dataSource);
        
        // redis存储token
        //        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        //        redisTokenStore.setSerializationStrategy(jsonSerializationStrategy);
        //        return redisTokenStore;
    }
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");
    }
    
    /**
     * 第三方客户端信息存储方式，对应oauth2数据库的表：oauth_client_details
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);
    }
}
