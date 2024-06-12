package org.jenga.dantong.notification;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FCMInitializer {


    @Value("${dantong.firebase-config}")
    private String FIREBASE_CONFIG_PATH;


    @PostConstruct
    public void initFcm() {

        try {
            FileInputStream fileInputStream = new FileInputStream(FIREBASE_CONFIG_PATH);
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(fileInputStream))
                .build();
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
                log.info("FirebaseApp initialization complete");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
