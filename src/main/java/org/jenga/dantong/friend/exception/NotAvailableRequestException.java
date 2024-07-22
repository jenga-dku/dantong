package org.jenga.dantong.friend.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class NotAvailableRequestException extends ApplicationException {
    public NotAvailableRequestException() {
        super(HttpStatus.BAD_REQUEST, "NOT_AVAILABLE_REQUEST");
    }
}
