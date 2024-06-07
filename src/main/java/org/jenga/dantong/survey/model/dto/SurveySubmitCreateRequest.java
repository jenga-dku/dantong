package org.jenga.dantong.survey.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveySubmitCreateRequest {

    private Long surveyId;
    private List<SurveyReplyCreateRequest> replyRequest;
}
