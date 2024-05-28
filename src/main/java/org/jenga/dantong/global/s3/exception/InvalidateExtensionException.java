package org.jenga.dantong.global.s3.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class InvalidateExtensionException extends ApplicationException {

    public InvalidateExtensionException() {
        super(HttpStatus.BAD_REQUEST, "INVALIDATE_EXTENSION");
    }

}
