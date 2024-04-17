package org.jenga.dantong.global.auth.jwt;

import java.time.Duration;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class SecretKey {

    private final String jwtSecretKey;
    private final Duration jwtValidityTime;
    private final Duration refreshValidityTime;

    public SecretKey(@Value("${security.jwt.token.secret-key}") String jwtSecretKey,
        @Value("${security.jwt.token.expire-length}")Duration jwtValidityTime,
        @Value("${security.jwt.token.expire-length-refresh}") Duration refreshValidityTime) {
        this.jwtSecretKey = jwtSecretKey;
        this.jwtValidityTime = jwtValidityTime;
        this.refreshValidityTime = refreshValidityTime;
    }
}