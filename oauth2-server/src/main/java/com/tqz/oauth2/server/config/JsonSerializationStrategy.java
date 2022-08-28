package com.tqz.oauth2.server.config;

import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.security.oauth2.provider.token.store.redis.StandardStringSerializationStrategy;
import org.springframework.stereotype.Component;

/**
 * <p>更改redis序列化方式为jackson2
 *
 * @author tianqingzhao
 * @since 2022/8/27 14:14
 */
@Component
public class JsonSerializationStrategy extends StandardStringSerializationStrategy {
    
    private Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
    
    @Override
    protected <T> T deserializeInternal(byte[] bytes, Class<T> clazz) {
        return (T) serializer.deserialize(bytes);
    }
    
    @Override
    protected byte[] serializeInternal(Object object) {
        return serializer.serialize(object);
    }
}
