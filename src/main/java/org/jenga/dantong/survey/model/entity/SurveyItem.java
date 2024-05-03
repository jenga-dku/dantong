package org.jenga.dantong.survey.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "survey_item")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
public class SurveyItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "survey_item_id")
    private int surveyItemId;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @Column(name = "tag")
    private Tag tag;

    @Column(name = "survey_id")
    private int surveyId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToOne
    private SurveyReply surveyReply;
}
