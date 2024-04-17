package org.jenga.dantong.post.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Table(name = "post")
@NoArgsConstructor
@Entity
public class Post {

    @Id
    private int postId;

    @Column(name = "userId")
    private int userId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "content")
    private String content;

    @Column(name = "shown")
    private boolean shown;


    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }

    @Builder
    public Post(int postId, int userId, String title, String description, String content, boolean shown) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.content = content;
        this.shown = shown;
    }
}
