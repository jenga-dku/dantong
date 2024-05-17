package org.jenga.dantong.survey.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "survey_reply")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
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

    public SurveyReply(String content, int userId) {
        this.content = content;
        this.userId = userId;
    }

    public void setSurveyItem(SurveyItem surveyItem) {
        this.surveyItem = surveyItem;
        surveyItem.setSurveyReply(this);
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void deleteReply() {
        surveyItem.setSurveyReply(null);
        this.surveyItem = null;
    }
}
