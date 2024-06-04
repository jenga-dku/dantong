package org.jenga.dantong.survey.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jenga.dantong.survey.model.entity.Tag;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SurveyItemUpdateRequest {

    private Long surveyItemId;
    private String title;
    private Tag tag;
    private List<String> options;
    private String description;
}
