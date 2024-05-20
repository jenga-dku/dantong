package org.jenga.dantong.survey.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jenga.dantong.global.base.BaseEntity;

@Table(name = "survey_item")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurveyItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "survey_item_id", insertable = false, updatable = false)
    private int surveyItemId;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Column(name = "tag")
    private Tag tag;

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

    public void setTag(Tag tag) {
        this.tag = tag;
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
