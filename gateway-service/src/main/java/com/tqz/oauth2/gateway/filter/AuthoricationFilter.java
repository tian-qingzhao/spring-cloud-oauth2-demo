package com.tqz.oauth2.gateway.filter;

import com.tqz.oauth2.gateway.constant.Constant;
import com.tqz.oauth2.gateway.entity.SystemErrorType;
import com.tqz.oauth2.gateway.entity.TokenInfo;
import com.tqz.oauth2.gateway.exception.GateWayException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>授权过滤器，授权过滤器一定要在认证过滤器之后执行
 *
 * @author tianqingzhao
 * @since 2022/8/27 17:17
 */
@Component
@Slf4j
public class AuthoricationFilter implements GlobalFilter, Ordered, InitializingBean {
    
    private List<String> shouldSkipUriList = new ArrayList<>();
    
    @Override
    public void afterPropertiesSet() throws Exception {
        shouldSkipUriList.add("/oauth/token");
        shouldSkipUriList.add("/order/selectById");
    }
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String reqUri = exchange.getRequest().getURI().getPath();
        if (shouldSkipUriList.contains(reqUri)) {
            log.info("请求不需要授权：{}", reqUri);
            return chain.filter(exchange);
        }
        
        TokenInfo tokenInfo = exchange.getAttribute(Constant.TOKEN_INFO_KEY);
        if (tokenInfo == null) {
            throw new GateWayException(SystemErrorType.INVALID_TOKEN);
        }
        
        List<String> urlList = Arrays.asList(tokenInfo.getAuthorities());
        boolean hasPremisson = false;
        for (String url : urlList) {
            if (url.contains(reqUri)) {
                hasPremisson = true;
            }
        }
        
        if (!hasPremisson) {
            log.error("请求没有权限：{}", reqUri);
            throw new GateWayException(SystemErrorType.FORBIDDEN);
        }
        
        return chain.filter(exchange);
    }
    
    @Override
    public int getOrder() {
        return 1;
    }
}
