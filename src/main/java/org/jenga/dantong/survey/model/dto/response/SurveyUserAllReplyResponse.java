package org.jenga.dantong.survey.model.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class SurveyUserAllReplyResponse {
    private Long submitId;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;

    public SurveyUserAllReplyResponse(Long submitId, String title, LocalDateTime startTime, LocalDateTime endTime, String status) {
        this.submitId = submitId;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }
}
