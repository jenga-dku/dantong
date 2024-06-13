//package org.jenga.dantong.notification;
//
//import com.google.firebase.messaging.FirebaseMessaging;
//import com.google.firebase.messaging.Message;
//import com.google.firebase.messaging.WebpushConfig;
//import com.google.firebase.messaging.WebpushNotification;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class NotificationService {
//
//    private final NotificationRedisRepository notificationRedisRepository;
//
//    public void sendEventNotification(String studentId) {
//        if (!hasKey(studentId)) {
//            return;
//        }
//        String token = getToken(studentId);
//        Message message = Message.builder()
//            .setWebpushConfig(WebpushConfig.builder().putHeader("ttl", "300")
//                .setNotification(new WebpushNotification("신청 완료 알림",
//                    "신청이 완료되었습니다."))
//                .build())
//            .setToken(token)
//            .build();
//        send(message);
//    }
//
//
//    private void send(Message message) {
//        FirebaseMessaging.getInstance().sendAsync(message);
//    }
//
//    private String getToken(String studentId) {
//        return notificationRedisRepository.getToken(studentId);
//    }
//
//    private boolean hasKey(String studentId) {
//        return notificationRedisRepository.hasKey(studentId);
//    }
//}
