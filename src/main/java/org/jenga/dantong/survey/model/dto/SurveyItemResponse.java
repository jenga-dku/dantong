package org.jenga.dantong.survey.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SurveyItemResponse {

    private int surveyItemId;
    private String title;
    private String description;
}
