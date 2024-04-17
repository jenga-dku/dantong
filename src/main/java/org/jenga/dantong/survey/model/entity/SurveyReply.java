package org.jenga.dantong.survey.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class SurveyReply {

    @Id
    @GeneratedValue
    private int id;
    private int surveyItemId;
    private String content;
}
