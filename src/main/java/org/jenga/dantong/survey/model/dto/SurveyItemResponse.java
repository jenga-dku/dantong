package org.jenga.dantong.survey.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.jenga.dantong.survey.model.entity.Tag;

@Getter
@Setter
public class SurveyItemResponse {

    private Tag tag;
    private String title;
    private String description;
}
