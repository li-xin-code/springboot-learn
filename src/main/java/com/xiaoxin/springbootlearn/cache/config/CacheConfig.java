package com.xiaoxin.springbootlearn.cache.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * @author lx
 * @date 2020/12/18
 */
@Configuration
public class CacheConfig {

    @Bean("empKeyGenerator")
    public KeyGenerator keyGenerator(){
        return (o, method, objects) -> objects[0]+"";
    }
}
