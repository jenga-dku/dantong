package org.jenga.dantong.survey.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jenga.dantong.post.model.entity.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "survey")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "survey_id", insertable = false, updatable = false)

    private int surveyId;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "survey")
    private List<SurveyItem> surveyItems = new ArrayList<>();

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "shown")
    @Builder.Default
    private boolean shown = true;

    public Survey(String title, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.title = title;
        this.description = description;
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

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

}
