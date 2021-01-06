package com.xiaoxin.springbootlearn.cache.interceptor;

import com.xiaoxin.springbootlearn.cache.annotation.CurrentLimiting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author lx
 * @date 2021/1/5
 */
@Component
public class CurrentLimitingInterceptor implements HandlerInterceptor {

    @Autowired
    @Qualifier("currentLimitingRedisTemplate")
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod method = (HandlerMethod) handler;
        CurrentLimiting currentLimiting = method.getMethodAnnotation(CurrentLimiting.class);
        if (currentLimiting == null) {
            return true;
        }

        int seconds = currentLimiting.seconds();
        int maxCount = currentLimiting.maxCount();
        String ip = request.getRemoteAddr();
        String uri = request.getServletPath();
        String key = ip + uri;
        Boolean hasKey = redisTemplate.hasKey(key);
        hasKey = hasKey != null && hasKey;
        if (!hasKey) {
            redisTemplate.opsForValue().set(key,"1");
            redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
            return true;
        } else {
            String val = redisTemplate.opsForValue().get(key);
            if (val == null){
                return false;
            }
            int count = Integer.parseInt(val);
            if (count < maxCount) {
                // key 加一
                redisTemplate.opsForValue().increment(key);
                return true;
            } else {
                // 拦截
                return false;
            }
        }


    }
}