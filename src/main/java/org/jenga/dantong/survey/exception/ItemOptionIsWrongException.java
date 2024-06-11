package org.jenga.dantong.survey.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class ItemOptionIsWrongException extends ApplicationException {

    public ItemOptionIsWrongException() {
        super(HttpStatus.BAD_REQUEST, "INVALID_OPTION");
    }
}
