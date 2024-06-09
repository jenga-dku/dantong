package org.jenga.dantong.survey.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.jenga.dantong.global.base.BaseEntity;
import org.jenga.dantong.post.model.entity.Post;
import org.jenga.dantong.user.model.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "survey")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Survey extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "survey_id", insertable = false, updatable = false)
    private Long surveyId;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.REMOVE)
    private List<SurveyItem> surveyItems = new ArrayList<>();

    @OneToMany(mappedBy = "survey", cascade = CascadeType.REMOVE)
    private List<SurveySubmit> surveySubmits = new ArrayList<>();

    @Column(name = "title")
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "shown")
    @Builder.Default
    private boolean shown = true;

    public Survey(String title, String description, Post post, LocalDateTime startTime,
                  LocalDateTime endTime) {
        this.title = title;
        this.description = description;
        this.post = post;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shown = true;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
