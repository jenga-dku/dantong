package org.jenga.dantong.survey.model.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jenga.dantong.survey.model.entity.SurveyItem;
import org.jenga.dantong.survey.model.entity.Tag;

@Getter
@Setter
public class SurveyItemResponse {

    private Long surveyItemId;
    private String title;
    private Tag tag;
    private List<String> options;
    private String description;

    @Builder
    public SurveyItemResponse(Long surveyItemId, String title, Tag tag, List<String> options,
        String description) {
        this.surveyItemId = surveyItemId;
        this.title = title;
        this.tag = tag;
        this.options = options;
        this.description = description;
    }

    public SurveyItemResponse(SurveyItem surveyItem) {
        this.surveyItemId = surveyItem.getSurveyItemId();
        this.title = surveyItem.getTitle();
        this.tag = surveyItem.getTag();
        this.options = surveyItem.getOptions();
        this.description = surveyItem.getDescription();
    }
}
