package com.tqz.oauth2.gateway.route;

import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.AbstractListener;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.tqz.oauth2.gateway.route.properties.GatewayNacosProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * <p>nacos配置中心监听路由
 *
 * @author tianqingzhao
 * @since 2022/8/28 11:19
 */
@Slf4j
public class NacosConfigListenerRoute implements InitializingBean {
    
    private GatewayNacosProperties gatewayNacosProperties;
    
    private GatewayRouteRefresher gatewayDynamicRoute;
    
    private ConfigService configService;
    
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
    public NacosConfigListenerRoute(GatewayNacosProperties gatewayNacosProperties,
            GatewayRouteRefresher gatewayDynamicRoute) {
        this.gatewayNacosProperties = gatewayNacosProperties;
        this.gatewayDynamicRoute = gatewayDynamicRoute;
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        initNacosConfigService();
        if (configService == null) {
            return;
        }
        
        String configStr = configService.getConfig(gatewayNacosProperties.getDataId(),
                gatewayNacosProperties.getGroup(), gatewayNacosProperties.getTimeoutMs());
        if (configStr == null) {
            return;
        }
        
        log.info("网关路由配置：{}", configStr);
        
        List<RouteDefinition> routeDefinitionList = convert(configStr);
        if (CollectionUtils.isEmpty(routeDefinitionList)) {
            return;
        }
        
        routeDefinitionList.forEach(item -> gatewayDynamicRoute.add(item));
        
        registerNacosListenerGetRoute();
    }
    
    /**
     * 注册nacos监听器获取路由，nacos配置中心通过 {@link Listener} 监听器实现配置刷新
     */
    private void registerNacosListenerGetRoute() {
        try {
            configService.addListener(gatewayNacosProperties.getDataId(), gatewayNacosProperties.getGroup(),
                    new AbstractListener() {
                        
                        @Override
                        public void receiveConfigInfo(String configInfo) {
                            List<RouteDefinition> routeDefinitionList = convert(configInfo);
                            gatewayDynamicRoute.refreshAll(routeDefinitionList);
                            
                        }
                    });
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 获取 {@link ConfigService} 对象
     */
    private void initNacosConfigService() {
        Properties properties = new Properties();
        
        properties.setProperty(PropertyKeyConst.SERVER_ADDR, gatewayNacosProperties.getServerAddr());
        properties.setProperty(PropertyKeyConst.NAMESPACE, gatewayNacosProperties.getNamespace());
        properties.setProperty(PropertyKeyConst.USERNAME, Objects.toString(gatewayNacosProperties.getUsername(), ""));
        properties.setProperty(PropertyKeyConst.PASSWORD, Objects.toString(gatewayNacosProperties.getPassword(), ""));
        
        try {
            configService = ConfigFactory.createConfigService(properties);
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 读取配置内容
     *
     * @param data 配置内容数据
     * @return {@link RouteDefinition} 路由集合
     */
    private <T> List<T> convert(String data) {
        try {
            CollectionType listType = OBJECT_MAPPER.getTypeFactory()
                    .constructCollectionType(ArrayList.class, RouteDefinition.class);
            return OBJECT_MAPPER.readValue(data, listType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
