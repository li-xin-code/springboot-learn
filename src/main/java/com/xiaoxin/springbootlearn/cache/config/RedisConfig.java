package com.xiaoxin.springbootlearn.cache.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;


/**
 * @author lx
 * @date 2020/12/23
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, String> currentLimitingRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(stringRedisSerializer());
        template.setValueSerializer(stringRedisSerializer());
        return template;
    }

    private RedisSerializer<String> stringRedisSerializer(){
        return new StringRedisSerializer();
    }

}