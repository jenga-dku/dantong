package org.jenga.dantong.survey.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
