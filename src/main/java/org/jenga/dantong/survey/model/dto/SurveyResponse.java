package org.jenga.dantong.survey.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class SurveyResponse {

    private String title;
    private String description;
    private Long postId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Builder.Default
    private List<SurveyItemResponse> surveyItems = new ArrayList<SurveyItemResponse>();
}
