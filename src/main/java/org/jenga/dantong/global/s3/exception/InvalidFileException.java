package org.jenga.dantong.global.s3.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class InvalidFileException extends ApplicationException {

    public InvalidFileException() {
        super(HttpStatus.BAD_REQUEST, "INVALID_FILE");
    }
}
