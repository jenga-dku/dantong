package org.jenga.dantong.survey.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class SurveyDueHasPassedException extends ApplicationException {
    public SurveyDueHasPassedException() {
        super(HttpStatus.BAD_REQUEST, "SURVEY_DUE_HAS_PASSED");
    }

}
