package org.jenga.dantong.post.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "post")
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @Builder
    public Post(int userId, String title, String description, String content, LocalDateTime date, boolean shown) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.content = content;
        this.date = date;
        this.shown = shown;
    }
}
