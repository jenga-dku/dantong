package org.jenga.dantong.survey.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class SurveyIsNotStartedException extends ApplicationException {
    public SurveyIsNotStartedException() {
        super(HttpStatus.BAD_REQUEST, "SURVEY_NOT_STARTED");
    }
}
