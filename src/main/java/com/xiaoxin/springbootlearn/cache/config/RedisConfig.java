package com.xiaoxin.springbootlearn.cache.config;

import com.xiaoxin.springbootlearn.cache.entity.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
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
    public RedisTemplate<Object, Employee> empRedisTemplate(
            /*@Qualifier("jedisConnectionFactory")*/ RedisConnectionFactory factory) {
        RedisTemplate<Object, Employee> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer<Employee> serializer = new Jackson2JsonRedisSerializer<>(Employee.class);
        template.setKeySerializer(new StringRedisSerializer());
        template.setDefaultSerializer(serializer);

        return template;
    }

//    @Bean
//    public RedisConnectionFactory jedisConnectionFactory() {
//        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration()
//                .master("lmaster")
//                .sentinel("192.168.1.2", 26379)
//                .sentinel("192.168.1.2", 26380)
//                .sentinel("192.168.1.2", 26381);
//        return new JedisConnectionFactory(sentinelConfig);
//    }
}