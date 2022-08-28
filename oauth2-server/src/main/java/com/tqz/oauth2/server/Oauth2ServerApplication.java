package com.tqz.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * 获取令牌：
 *  1.密码模式访问：http://localhost:9123/oauth/token?username=zhangsan&password=123456&grant_type=password
 *  2.授权码模式访问：http://localhost:9123/oauth/authorize?response_type=code
 *  &client_id=protal-app&redirect_uri=https://www.baidu.com&state=abc
 *
 * @author tianqingzhao
 * @since 2022/8/5 15:05
 */
@SpringBootApplication
public class Oauth2ServerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class, args);
    }
}
