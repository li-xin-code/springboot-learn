package com.xiaoxin.springbootlearn.cache.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lx
 * @date 2020/12/18
 */
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean("empKeyGenerator")
    public KeyGenerator keyGenerator(){
        return (o, method, objects) -> objects[0]+"";
    }

    @Bean
    public RedisCacheManager cacheManager(/*@Qualifier("jedisConnectionFactory")*/RedisConnectionFactory factory) {
        return RedisCacheManager.builder(factory)
                .cacheDefaults(defaultCacheConfig(10000))
                .withInitialCacheConfigurations(initCacheConfigMap())
                .transactionAware()
                .build();
    }

    private RedisCacheConfiguration defaultCacheConfig(Integer second) {
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(second))
                .serializeKeysWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(serializer))
                .disableCachingNullValues();
    }

    private Map<String, RedisCacheConfiguration> initCacheConfigMap() {
        Map<String, RedisCacheConfiguration> map = new HashMap<>(16);
        map.put("emp",this.defaultCacheConfig(1000000));
        map.put("emp2",this.defaultCacheConfig(2000000));
        map.put("dep",this.defaultCacheConfig(2000000));
        return map;
    }
}
