package org.jenga.dantong.user.service;

import java.time.Clock;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.jenga.dantong.user.model.dto.UserInfo;
import org.jenga.dantong.user.repository.SignupRedisRepository;
import org.jenga.dantong.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    public static final String DKU_AUTH_NAME = "dku";

    private final Clock clock;
    private final UserRepository userRepository;
    private final SignupRedisRepository redisRepository;

    public void getStudentInfo(String signupToken) {
        Instant now = Instant.now(clock);
        redisRepository.getAuthPayload(signupToken, DKU_AUTH_NAME, UserInfo.class, now);
    }

    public boolean deleteStudentAuth(String signupToken) {
        return redisRepository.deleteAuthPayload(signupToken, DKU_AUTH_NAME);
    }


}
