package org.jenga.dantong.user.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class WrongPasswordException extends ApplicationException {

    public WrongPasswordException() {
        super(HttpStatus.BAD_REQUEST, "WRONG_PASSWORD");
    }
}
