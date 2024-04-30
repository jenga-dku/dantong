package org.jenga.dantong.user.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import org.jenga.dantong.global.base.KeyValueRepository;
import org.jenga.dantong.global.redis.config.RedisKeys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SignupRedisRepository extends KeyValueRepository {

    private final Duration duration;

    public SignupRedisRepository(StringRedisTemplate redisTemplate,
        ObjectMapper objectMapper, @Value("${app.auth.signup-expires}") Duration duration) {
        super(redisTemplate, objectMapper, RedisKeys.SIGNUP_KEY);
        this.duration = duration;
    }

    public <T> Optional<T> getAuthPayload(String signupToken, String authName, Class<T> clazz, Instant now) {
        String key = makeValueKey(signupToken, authName);
        return get(key, clazz, now);
    }

    public void setAuthPayload(String signupToken, String authName, Object data, Instant now) {
        String key = makeValueKey(signupToken, authName);
        setValue(key, data, now, duration);
    }

    public boolean deleteAuthPayload(String signupToken, String authName) {
        String key = makeValueKey(signupToken, authName);
        return remove(key);
    }

    public String makeValueKey(String signupToken, String authName) {
        return signupToken + RedisKeys.KEY_DELIMITER + authName;
    }
}
