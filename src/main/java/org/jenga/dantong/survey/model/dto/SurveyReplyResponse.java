package org.jenga.dantong.survey.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SurveyReplyResponse {

    private int surveyItemId;
    private String content;
}
