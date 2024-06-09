package org.jenga.dantong.user.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class EmailCodeNotExistException extends ApplicationException {

    public EmailCodeNotExistException() {
        super(HttpStatus.NOT_FOUND, "EMAIL_CODE_NOT_FOUND");
    }
}
