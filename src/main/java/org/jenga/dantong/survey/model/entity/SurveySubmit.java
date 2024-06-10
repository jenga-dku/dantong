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

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SurveySubmit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "survey_item_id", insertable = false, updatable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(mappedBy = "surveySubmit", cascade = CascadeType.REMOVE)
    private List<SurveyReply> surveyReplies;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public SurveySubmit(User user, Survey survey) {
        this.user = user;
        this.survey = survey;
    }

    public void setSurveyReplies(List<SurveyReply> surveyReplies) {
        this.surveyReplies = surveyReplies;
    }
}
