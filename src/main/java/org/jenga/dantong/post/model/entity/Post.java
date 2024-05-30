package org.jenga.dantong.post.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.jenga.dantong.global.base.BaseEntity;
import org.jenga.dantong.survey.model.entity.Survey;
import org.jenga.dantong.user.model.entity.User;

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
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id", insertable = false, updatable = false)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "shown")
    private boolean shown;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @OneToMany(mappedBy = "post")
    @Builder.Default
    private List<Survey> surveys = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @Builder.Default
    private List<PostFile> files = new ArrayList<>();

}
