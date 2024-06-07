package org.jenga.dantong.survey.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SurveyReplyCreateRequest {

    private Long surveyItemId;
    private String content;
}
