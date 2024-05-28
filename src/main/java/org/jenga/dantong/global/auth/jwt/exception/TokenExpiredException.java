package org.jenga.dantong.global.auth.jwt.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class TokenExpiredException extends ApplicationException {

    public TokenExpiredException() {
        super(HttpStatus.UNAUTHORIZED, "TOKEN_EXPIRED");
    }
}
