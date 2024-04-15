package org.jenga.dantong.global.auth;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class SecretKey {

    private final String jwtSecretKey;
    private final Long jwtValidityTime;
    private final Long refreshValidityTime;

    public SecretKey(@Value("${security.jwt.token.secret-key}") String jwtSecretKey,
        @Value("${security.jwt.token.expire-length}")Long jwtValidityTime,
        @Value("${security.jwt.token.expire-length-refresh}") Long refreshValidityTime) {
        this.jwtSecretKey = jwtSecretKey;
        this.jwtValidityTime = jwtValidityTime;
        this.refreshValidityTime = refreshValidityTime;
    }
}