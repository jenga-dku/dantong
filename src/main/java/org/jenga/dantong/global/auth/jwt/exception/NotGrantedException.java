package org.jenga.dantong.global.auth.jwt.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class NotGrantedException extends ApplicationException {

    public NotGrantedException() {
        super(HttpStatus.FORBIDDEN, "NO_PERMISSION_TO_ACCESS");
    }

}
