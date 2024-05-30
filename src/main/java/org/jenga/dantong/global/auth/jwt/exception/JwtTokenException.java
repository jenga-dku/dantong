package org.jenga.dantong.global.auth.jwt.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class JwtTokenException extends ApplicationException {

    public JwtTokenException() {
        super(HttpStatus.UNAUTHORIZED, "TOKEN_NOT_VALIDATED");
    }
}
