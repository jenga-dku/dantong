package org.jenga.dantong.survey.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class SurveyItemNotFoundException extends ApplicationException {

    public SurveyItemNotFoundException() {
        super(HttpStatus.NOT_FOUND, "SURVEY_ITEM_NOT_FOUND");
    }
}
