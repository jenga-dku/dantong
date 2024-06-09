package org.jenga.dantong.survey.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class SurveySubmitNotFoundException extends ApplicationException {

    public SurveySubmitNotFoundException() {
        super(HttpStatus.NOT_FOUND, "SURVEY_SUBMIT_NOT_FOUND_EXCEPTION");
    }
}
