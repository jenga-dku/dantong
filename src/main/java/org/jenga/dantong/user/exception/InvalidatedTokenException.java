package org.jenga.dantong.user.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class InvalidatedTokenException extends ApplicationException {

    public InvalidatedTokenException() {
        super(HttpStatus.BAD_REQUEST, "INVALIDATED_TOKEN");
    }
}
