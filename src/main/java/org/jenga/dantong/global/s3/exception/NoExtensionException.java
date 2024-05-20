package org.jenga.dantong.global.s3.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class NoExtensionException extends ApplicationException {

    public NoExtensionException() {
        super(HttpStatus.BAD_REQUEST, "NO_EXTENSION");
    }
}
