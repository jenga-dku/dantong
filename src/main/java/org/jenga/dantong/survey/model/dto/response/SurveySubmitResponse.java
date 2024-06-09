package org.jenga.dantong.survey.model.dto.response;

import java.util.List;
import lombok.Getter;

@Getter
public class SurveySubmitResponse {

    private Long surveyId;
    private List<SurveyReplyResponse> surveyReplies;

    public SurveySubmitResponse(Long surveyId, List<SurveyReplyResponse> surveyReplies) {
        this.surveyId = surveyId;
        this.surveyReplies = surveyReplies;
    }
}
