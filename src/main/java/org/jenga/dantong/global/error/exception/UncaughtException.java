package org.jenga.dantong.global.error.exception;

import org.springframework.http.HttpStatus;

public class UncaughtException extends ApplicationException {

    public UncaughtException(Throwable e) {
        super(e, HttpStatus.INTERNAL_SERVER_ERROR, "unexpected");
    }

    @Override
    public String getCode() {
        return getCause().getClass().getSimpleName();
    }


}
