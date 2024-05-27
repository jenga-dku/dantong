package org.jenga.dantong.global.s3.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class EmptyFileException extends ApplicationException {

    public EmptyFileException() {
        super(HttpStatus.BAD_REQUEST, "EMPTY_FILE");
    }

}
