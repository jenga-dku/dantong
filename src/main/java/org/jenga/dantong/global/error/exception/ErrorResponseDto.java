package org.jenga.dantong.global.error.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import lombok.Getter;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Getter
public class ErrorResponseDto {

    private final String timestamp;
    private final String trackingId;
    private final HttpStatus status;
    private final String code;
    private final List<Object> message;


    public ErrorResponseDto(MessageSource messageSource, Locale locale, ApplicationException e) {
        this.timestamp = LocalDateTime.now().toString();
        this.trackingId = UUID.randomUUID().toString();
        this.status = e.getStatus();
        this.code = e.getCode();
        this.message = e.getMessages(messageSource, locale);
    }

    public ErrorResponseDto(MessageSource messageSource, Locale locale,
        MethodArgumentNotValidException e) {
        this.timestamp = LocalDateTime.now().toString();
        this.trackingId = UUID.randomUUID().toString();
        this.status = HttpStatus.resolve(e.getStatusCode().value());
        this.code = e.getClass().getSimpleName();
        this.message = List.of(e.getDetailMessageArguments(messageSource, locale));
    }
}