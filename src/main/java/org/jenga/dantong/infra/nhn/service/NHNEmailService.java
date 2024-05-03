package org.jenga.dantong.infra.nhn.service;

import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.infra.nhn.model.NhnMessage;
import org.jenga.dantong.infra.nhn.model.NhnMessage.Receiver;
import org.jenga.dantong.infra.nhn.model.ReceiveType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Slf4j
public class NHNEmailService {

    private final WebClient webClient;
    @Value("${nhn.email.url}")
    private String NHN_URL;
    @Value("${nhn.email.secret-key}")
    private String NHN_EMAIL_KEY;
    @Value("${nhn.email.sender-mail}")
    private String NHN_SENDER;

    public void sendMessage(String studentId, String title, String text) {
        NhnMessage nhnMessage = makeMessage(studentId, title, text);
        log.info(nhnMessage.getTitle());
        ResponseEntity<String> response = webClient.post()
            .uri(NHN_URL)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .header("X-Secret-Key", NHN_EMAIL_KEY)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(nhnMessage)
            .retrieve()
            .toEntity(String.class)
            .block();
        if (response == null || response.getStatusCode().isError()) {
            throw new RuntimeException("메일 전송에 실패했습니다.");
        }
        log.info("message = {}", response.getBody());
    }

    private NhnMessage makeMessage(String studentId, String title, String text) {
        ArrayList<Receiver> receivers = new ArrayList<>();
        Receiver receiver = new Receiver(studentId + "@dankook.ac.kr", ReceiveType.MRT0);
        receivers.add(receiver);

        return NhnMessage.builder()
            .senderAddress(NHN_SENDER)
            .title(title)
            .body(text)
            .receiverList(receivers)
            .build();
    }
}
