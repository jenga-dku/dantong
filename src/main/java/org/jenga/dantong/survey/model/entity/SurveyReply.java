package org.jenga.dantong.survey.model.entity;

import jakarta.persistence.*;
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

    @ManyToOne
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
