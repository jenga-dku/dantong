package org.jenga.dantong.survey.model.entity;

import jakarta.persistence.*;
import lombok.*;
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

}
