package org.jenga.dantong.user.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class EmailCodeNotMatchException extends ApplicationException {

    public EmailCodeNotMatchException() {
        super(HttpStatus.BAD_REQUEST, "EMAIL_CODE_NOT_MATCHED");
    }
}
