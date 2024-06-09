package org.jenga.dantong.survey.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class AlreadyHasSubmitException extends ApplicationException {

    public AlreadyHasSubmitException() {
        super(HttpStatus.BAD_REQUEST, "ALREADY_HAS_SUBMIT_EXCEPTION");
    }
}
