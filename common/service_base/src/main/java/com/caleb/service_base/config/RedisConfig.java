package com.caleb.service_base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置类
 */
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //String 类型 key序列器
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //String 类型 value序列器
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //Hash 类型 key序列器
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        //Hash 类型 value序列器
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        //配置工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
