package org.jenga.dantong.survey.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "survey_reply")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveyReply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reply_id", insertable = false, updatable = false)
    private int replyId;

    @OneToOne
    @JoinColumn(name = "survey_item_id")
    private SurveyItem surveyItem;

    @Column(name = "content")

    private String content;

    @Column(name = "user_id")
    private int userId;
}
