package org.jenga.dantong.survey.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class SurveyReplyNotFoundException extends ApplicationException {

    public SurveyReplyNotFoundException() {
        super(HttpStatus.NOT_FOUND, "SURVEY_REPLY_NOT_FOUND");
    }
}
