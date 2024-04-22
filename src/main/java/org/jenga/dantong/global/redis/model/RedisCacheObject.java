package org.jenga.dantong.global.redis.model;

import java.time.Instant;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RedisCacheObject<T> {

    private final Instant expiredAt;
    private final T value;
}
