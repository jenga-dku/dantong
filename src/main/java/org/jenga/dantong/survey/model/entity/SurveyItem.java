package org.jenga.dantong.survey.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "survey_item")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurveyItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "survey_item_id", insertable = false, updatable = false)
    private int surveyItemId;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "shown")
    @Builder.Default
    private boolean shown = true;

    @OneToOne(mappedBy = "surveyItem")
    private SurveyReply surveyReply;

    public void setSurvey(Survey survey) {
        this.survey = survey;
        survey.getSurveyItems().add(this);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    public void setSurveyReply(SurveyReply reply) {
        this.surveyReply = reply;
    }
}
