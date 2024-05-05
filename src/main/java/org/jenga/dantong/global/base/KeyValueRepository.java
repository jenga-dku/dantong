package org.jenga.dantong.global.base;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.global.redis.model.RedisCacheObject;
import org.springframework.data.redis.core.StringRedisTemplate;

@RequiredArgsConstructor
@Slf4j
public class KeyValueRepository {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;
    private final String rootKey;

    public <T> RedisCacheObject<T> setValue(String key, T data, Instant now) {
        return setValue(key, data, now, null);
    }

    public <T> RedisCacheObject<T> setValue(String key, T data, Instant now, Duration duration) {
        Instant expiredAt;
        if (duration != null) {
            expiredAt = now.plus(duration);
        } else {
            expiredAt = Instant.MAX;
        }

        RedisCacheObject<T> object = new RedisCacheObject<>(expiredAt, data);
        String value;
        try {
            value = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        redisTemplate.opsForHash().put(rootKey, key, value);
        return object;
    }

    public <T> Optional<RedisCacheObject<T>> getCacheObject(String key, Class<T> clazz,
        Instant now) {
        JavaType type = objectMapper.getTypeFactory().constructType(clazz);
        return getCacheObject(key, type, now);
    }

    public <T> Optional<RedisCacheObject<T>> getCacheObject(String key, JavaType type,
        Instant now) {
        Object value = redisTemplate.opsForHash().get(rootKey, key);
        if (value == null) {
            return Optional.empty();
        }
        try {
            type = objectMapper.getTypeFactory()
                .constructParametricType(RedisCacheObject.class, type);
            RedisCacheObject<T> auth = objectMapper.readValue((String) value, type);
            if (now.isAfter(auth.getExpiredAt())) {
                remove(key);
                return Optional.empty();
            }
            return Optional.of(auth);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> Optional<T> get(String key, Class<T> clazz, Instant now) {
        return getCacheObject(key, clazz, now)
            .map(RedisCacheObject::getValue);
    }

    public <T> Optional<T> get(String key, JavaType type, Instant now) {
        Optional<RedisCacheObject<T>> cacheObject = getCacheObject(key, type, now);
        return cacheObject.map(RedisCacheObject::getValue);
    }

    public boolean remove(String key) {
        return redisTemplate.opsForHash().delete(rootKey, key) > 0;
    }
}
