package org.jenga.dantong.survey.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SurveyReplyUpdateRequest {

    private Long replyId;
    private Long surveyItemId;
    private String content;

    private Long userId;
}
