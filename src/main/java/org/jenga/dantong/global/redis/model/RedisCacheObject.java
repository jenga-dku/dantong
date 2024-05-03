package org.jenga.dantong.global.redis.model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RedisCacheObject<T> {

    private Instant expiredAt;
    private T value;
}
