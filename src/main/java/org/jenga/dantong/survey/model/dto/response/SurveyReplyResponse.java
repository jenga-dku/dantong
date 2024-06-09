package org.jenga.dantong.survey.model.dto.response;

import lombok.Getter;

@Getter
public class SurveyReplyResponse {

    private Long surveyReplyId;
    private SurveyItemResponse surveyItem;
    private String content;

    public SurveyReplyResponse(Long surveyReplyId, SurveyItemResponse surveyItem, String content) {
        this.surveyReplyId = surveyReplyId;
        this.surveyItem = surveyItem;
        this.content = content;
    }
}
