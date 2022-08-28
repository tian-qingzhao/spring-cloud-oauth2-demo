package com.tqz.oauth2.gateway.filter;

import com.tqz.oauth2.gateway.constant.Constant;
import com.tqz.oauth2.gateway.entity.SystemErrorType;
import com.tqz.oauth2.gateway.entity.TokenInfo;
import com.tqz.oauth2.gateway.exception.GateWayException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>认证过滤器
 *
 * @author tianqingzhao
 * @since 2022/8/27 16:41
 */
@Component
@Slf4j
public class AuthorizationFilter implements GlobalFilter, Ordered, InitializingBean {
    
    @Autowired
    private RestTemplate restTemplate;
    
    private List<String> shouldSkipUriList = new ArrayList<>();
    
    @Override
    public void afterPropertiesSet() throws Exception {
        shouldSkipUriList.add("/oauth/token");
        shouldSkipUriList.add("/order/selectById");
    }
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String reqUri = exchange.getRequest().getURI().getPath();
        log.info("请求的uri:{}", reqUri);
        
        if (shouldSkipUriList.contains(reqUri)) {
            log.info("不需要认证的请求:{}", reqUri);
            return chain.filter(exchange);
        }
        
        String authorization = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (StringUtils.isEmpty(authorization)) {
            log.error("需要认证的请求:{}，请求头为空", reqUri);
            throw new GateWayException(SystemErrorType.INVALID_TOKEN);
        }
        
        TokenInfo tokenInfo = null;
        try {
            tokenInfo = getTokenInfo(authorization);
        } catch (Exception e) {
            log.error("获取token失败", e);
            throw new GateWayException(SystemErrorType.INVALID_TOKEN);
        }
        
        ServerHttpRequest request = exchange.getRequest().mutate().header("userName", tokenInfo.getUserName()).build();
        ServerWebExchange serverWebExchange = exchange.mutate().request(request).build();
        serverWebExchange.getAttributes().put(Constant.TOKEN_INFO_KEY, tokenInfo);
        
        return chain.filter(serverWebExchange);
    }
    
    private TokenInfo getTokenInfo(String authHeader) {
        String token = StringUtils.substringAfter(authHeader, "bearer ");
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(Constant.GATEWAY_SERVICE_NAME, Constant.GATEWAY_SERVICE_SECRET);
        
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("token", token);
        
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
        
        ResponseEntity<TokenInfo> response = restTemplate.exchange(Constant.CHECK_TOKEN_URL, HttpMethod.POST, entity,
                TokenInfo.class);
        
        log.info("token info :" + response.getBody().toString());
        
        return response.getBody();
    }
    
    @Override
    public int getOrder() {
        return 0;
    }
}
