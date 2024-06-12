package org.jenga.dantong.survey.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class InvalidSurveyReplyException extends ApplicationException {
    public InvalidSurveyReplyException() {
        super(HttpStatus.BAD_REQUEST, "INVALID_REPLY");
    }
}
