package org.jenga.dantong.survey.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class SurveyNotFoundException extends ApplicationException {

    public SurveyNotFoundException() {
        super(HttpStatus.NOT_FOUND, "SURVEY_NOT_FOUND");
    }
}
