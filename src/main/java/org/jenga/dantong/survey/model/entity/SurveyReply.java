package org.jenga.dantong.survey.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.jenga.dantong.global.base.BaseEntity;
import org.jenga.dantong.user.model.entity.User;

@Table(name = "survey_reply")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SurveyReply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reply_id", insertable = false, updatable = false)
    private Long replyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_item_id")
    private SurveyItem surveyItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_submit_id")
    private SurveySubmit surveySubmit;

    @Column(name = "content")
    private String content;

    @JoinColumn(name = "user_id")
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public SurveyReply(String content, SurveySubmit surveySubmit, User user) {
        this.content = content;
        this.surveySubmit = surveySubmit;
        this.user = user;
    }

    public void setSurveyItem(SurveyItem surveyItem) {
        this.surveyItem = surveyItem;
        surveyItem.setSurveyReply(this);
    }

    public void setContent(String content) {
        this.content = content;
    }
}
