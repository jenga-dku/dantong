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

    private int replyId;
    private int surveyItemId;
    private String content;

    private int userId;
}
