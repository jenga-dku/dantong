package org.jenga.dantong.survey.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jenga.dantong.survey.model.entity.Tag;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SurveyItemCreateRequest {

    private String title;
    private Tag tag;
    private String description;
    private List<String> options;
}