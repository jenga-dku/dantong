package org.jenga.dantong.survey.model.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AllRepliesResponse {

    private SurveyItemResponse surveyItemResponse;
    private List<SurveyUserReplyResponse> replies;

}
