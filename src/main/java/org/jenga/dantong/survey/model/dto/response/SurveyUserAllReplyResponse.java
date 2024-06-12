package org.jenga.dantong.survey.model.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SurveyUserAllReplyResponse {
    private Long surveyId;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;

    public SurveyUserAllReplyResponse(Long surveyId, String title, LocalDateTime startTime, LocalDateTime endTime, String status) {
        this.surveyId = surveyId;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }
}
