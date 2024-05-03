package org.jenga.dantong.post.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.jenga.dantong.survey.model.entity.Survey;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id", insertable = false, updatable = false)
    private int postId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    @Column(name = "written_date")
    private LocalDateTime date;

    @Column(name = "shown")
    private boolean shown;

    @OneToMany(mappedBy = "post")
    private List<Survey> surveys = new ArrayList<Survey>();

}
