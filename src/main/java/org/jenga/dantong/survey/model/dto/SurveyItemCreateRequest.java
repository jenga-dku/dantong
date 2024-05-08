package org.jenga.dantong.survey.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jenga.dantong.survey.model.entity.Tag;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SurveyItemCreateRequest {

    private Tag tag;
    private String title;
    private String description;
}