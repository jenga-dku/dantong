package org.jenga.dantong.survey.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jenga.dantong.global.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

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
    private Long surveyItemId;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "tag")
    @Enumerated(EnumType.STRING)
    private Tag tag;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "options")
    private List<String> options = new ArrayList<>();

    @Column(name = "shown")
    @Builder.Default
    private boolean shown = true;

    @OneToMany(mappedBy = "surveyItem")
    private List<SurveyReply> surveyReplys = new ArrayList<>();

    public SurveyItem(Survey survey, String title, Tag tag) {
        this.survey = survey;
        this.title = title;
        this.tag = tag;
        this.shown = true;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
        survey.getSurveyItems().add(this);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    public void setSurveyReply(SurveyReply reply) {
        getSurveyReplys().add(reply);
    }
}
