package org.jenga.dantong.survey.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jenga.dantong.survey.model.entity.Tag;

@Getter
@Setter
@Builder
public class SurveyItemResponse {

    private int surveyItemId;
    private Tag tag;
    private String title;
    private String description;
}
