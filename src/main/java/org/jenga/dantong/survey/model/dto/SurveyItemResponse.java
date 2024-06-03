package org.jenga.dantong.survey.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jenga.dantong.survey.model.entity.Tag;

import java.util.List;

@Getter
@Setter
@Builder
public class SurveyItemResponse {

    private Long surveyItemId;
    private String title;
    private Tag tag;
    private String description;
    private List<String> options;
}
