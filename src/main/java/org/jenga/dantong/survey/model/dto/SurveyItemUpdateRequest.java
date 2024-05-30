package org.jenga.dantong.survey.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jenga.dantong.survey.model.entity.Tag;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SurveyItemUpdateRequest {

    private Long surveyItemId;
    private String title;
    private Tag tag;
    private List<String> options;
}
