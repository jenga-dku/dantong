package org.jenga.dantong.global.error.exception;

import java.util.Locale;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public class ControllerAdvisor {

    private final MessageSource messageSource;
    private final ErrorLogService errorLogService;
    private boolean isEnabledTest;

    @ExceptionHandler
    protected ResponseEntity<ErrorResponseDto> localizedException(ApplicationException e,
        Locale locale) {
        ErrorResponseDto dto = new ErrorResponseDto(messageSource, locale, e);
        log.error("A problem has occurred in controller advice: [id={}]", dto.getTrackingId(), e);
        return filter(e, ResponseEntity.status(e.getStatus()).body(dto));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> exception(MethodArgumentNotValidException e,
        Locale locale) {
        log.error("MethodArgumentNotValidException : " + e.getMessage());
        ErrorResponseDto dto = new ErrorResponseDto(messageSource, locale, e);
        return filter(e, ResponseEntity.status(e.getStatusCode()).body(dto));
    }

    private ResponseEntity<ErrorResponseDto> filter(Throwable t,
        ResponseEntity<ErrorResponseDto> entity) {
        ErrorResponseDto dto = entity.getBody();
        if (isEnabledTest && dto != null) {
            errorLogService.logError(dto.getTrackingId(), t, dto);
        }
        return entity;
    }
}
