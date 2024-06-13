//package org.jenga.dantong.notification;
//
//import lombok.RequiredArgsConstructor;
//import org.jenga.dantong.user.model.dto.request.LoginRequest;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Repository;
//
//@Repository
//@RequiredArgsConstructor
//public class NotificationRedisRepository {
//
//    private final StringRedisTemplate tokenRedisTemplate;
//
//    public void saveToken(LoginRequest loginRequest) {
//        tokenRedisTemplate.opsForValue()
//            .set(loginRequest.getStudentId(), loginRequest.getStudentId());
//    }
//
//    public String getToken(String studentId) {
//        return tokenRedisTemplate.opsForValue().get(studentId);
//    }
//
//    public void deleteToken(String studentId) {
//        tokenRedisTemplate.delete(studentId);
//    }
//
//    public boolean hasKey(String studentId) {
//        return tokenRedisTemplate.hasKey(studentId);
//    }
//}
//
