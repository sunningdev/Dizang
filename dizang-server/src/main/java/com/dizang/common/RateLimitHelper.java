package com.dizang.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Component
public class RateLimitHelper {

    private final StringRedisTemplate redisTemplate;
    private final Map<String, Long> memoryStore = new ConcurrentHashMap<>();
    private final Map<String, Long> memoryExpiry = new ConcurrentHashMap<>();

    public RateLimitHelper(@Autowired(required = false) StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public long increment(String key, long ttlSeconds) {
        if (redisTemplate != null) {
            Long count = redisTemplate.opsForValue().increment(key);
            if (count != null && count == 1) {
                redisTemplate.expire(key, ttlSeconds, TimeUnit.SECONDS);
            }
            return count == null ? 0 : count;
        }
        cleanExpired();
        long count = memoryStore.merge(key, 1L, Long::sum);
        memoryExpiry.putIfAbsent(key, System.currentTimeMillis() + ttlSeconds * 1000);
        return count;
    }

    public boolean exists(String key) {
        if (redisTemplate != null) {
            return Boolean.TRUE.equals(redisTemplate.hasKey(key));
        }
        cleanExpired();
        Long expiry = memoryExpiry.get(key);
        return expiry != null && expiry > System.currentTimeMillis();
    }

    public void set(String key, String value, long ttlSeconds) {
        if (redisTemplate != null) {
            redisTemplate.opsForValue().set(key, value, ttlSeconds, TimeUnit.SECONDS);
            return;
        }
        memoryStore.put(key, 1L);
        memoryExpiry.put(key, System.currentTimeMillis() + ttlSeconds * 1000);
    }

    private void cleanExpired() {
        long now = System.currentTimeMillis();
        memoryExpiry.entrySet().removeIf(e -> {
            if (e.getValue() <= now) {
                memoryStore.remove(e.getKey());
                return true;
            }
            return false;
        });
    }
}
