package org.jenga.dantong.survey.model.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SurveyUserAllReplyResponse {
    private Long surveyId;
    private String title;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;
}
