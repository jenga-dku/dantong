package org.jenga.dantong.survey.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "survey_reply")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
public class SurveyReply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reply_id")
    private int replyId;

    @OneToOne
    private SurveyItem surveyItem;

    @Column(name = "content")
    private String content;
}
