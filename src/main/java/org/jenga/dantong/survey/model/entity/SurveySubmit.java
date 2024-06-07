package org.jenga.dantong.survey.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jenga.dantong.user.model.entity.User;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SurveySubmit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "survey_item_id", insertable = false, updatable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @OneToMany(mappedBy = "surveySubmit")
    private List<SurveyReply> surveyReplies;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public SurveySubmit(User user, Survey survey) {
        this.user = user;
        this.survey = survey;
    }

    public void setSurveyReplies(List<SurveyReply> surveyReplies) {
        this.surveyReplies = surveyReplies;
    }
}
