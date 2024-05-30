package org.jenga.dantong.survey.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SurveyUserReplyResponse {

    private Long surveyItemId;
    private String content;
}
