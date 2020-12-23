package com.xiaoxin.springbootlearn.cache.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xiaoxin.springbootlearn.cache.entity.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author lx
 * @date 2020/12/23
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<Object, Employee> empRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<Object, Employee> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer<Employee> serializer = new Jackson2JsonRedisSerializer<>(Employee.class);
        template.setKeySerializer(new StringRedisSerializer());
        template.setDefaultSerializer(serializer);

        return template;
    }

//    @Bean
//    public RedisCacheManager empCacheManager(RedisTemplate<Object,Employee> empRedisTemplate) {
//        RedisCacheManager manager = new RedisCacheManager(empRedisTemplate);
//        manager.se
//        return manager;
//    }

}
