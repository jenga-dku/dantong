package org.jenga.dantong.survey.model.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jenga.dantong.survey.model.entity.Tag;

@Getter
@Setter
@Builder
public class SurveyItemResponse {

    private Long surveyItemId;
    private String title;
    private Tag tag;
    private List<String> options;
    private String description;
}
