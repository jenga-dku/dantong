package org.jenga.dantong.global.auth;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class AccessTokenRequiredException extends ApplicationException {

    public AccessTokenRequiredException() {
        super(HttpStatus.UNAUTHORIZED, "ACCESS_TOKEN_REQUIRED");
    }
}
