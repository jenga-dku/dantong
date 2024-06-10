package org.jenga.dantong.survey.model.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.jenga.dantong.survey.model.entity.Survey;

@Getter
@Setter
public class SurveyAdminResponse {

    private Long surveyId;
    private String title;
    private Long postId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private Long submitCount;

    public SurveyAdminResponse(Survey survey, String status, Long submitCount) {
        this.surveyId = survey.getSurveyId();
        this.title = survey.getTitle();
        this.postId = survey.getPost().getPostId();
        this.startTime = survey.getStartTime();
        this.endTime = survey.getEndTime();
        this.status = status;
        this.submitCount = submitCount;
    }
}
