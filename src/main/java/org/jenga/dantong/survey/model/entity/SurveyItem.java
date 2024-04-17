package org.jenga.dantong.survey.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class SurveyItem {

    @Id
    @GeneratedValue
    private int surveyItemId;
    private Tag tag;
    private int surveyId;
    private String title;
    private String description;
}
