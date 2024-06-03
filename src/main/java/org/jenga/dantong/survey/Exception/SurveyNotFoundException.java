package org.jenga.dantong.survey.Exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class SurveyNotFoundException extends ApplicationException {

    public SurveyNotFoundException() {
        super(HttpStatus.NOT_FOUND, "SUVEY_NOT_FOUND");
    }
}
