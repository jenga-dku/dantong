package org.jenga.dantong.survey.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jenga.dantong.user.model.entity.User;

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
    private Long replyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_item_id")
    private SurveyItem surveyItem;

    @Column(name = "content")
    private String content;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    public SurveyReply(String content, User user) {
        this.content = content;
        this.user = user;
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
