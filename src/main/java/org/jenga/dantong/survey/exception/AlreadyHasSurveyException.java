package org.jenga.dantong.survey.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class AlreadyHasSurveyException extends ApplicationException {

    public AlreadyHasSurveyException() {
        super(HttpStatus.BAD_REQUEST, "ALREADY_HAS_SURVEY");
    }
}
