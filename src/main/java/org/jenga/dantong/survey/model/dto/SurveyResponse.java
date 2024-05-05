package org.jenga.dantong.survey.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class SurveyResponse {

    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Builder.Default
    private List<SurveyItemResponse> surveyItems = new ArrayList<SurveyItemResponse>();
}
