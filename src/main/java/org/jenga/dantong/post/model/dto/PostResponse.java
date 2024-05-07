package org.jenga.dantong.post.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jenga.dantong.post.model.entity.Category;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponse {
    private int userId;
    private String title;
    private String description;
    private String content;
    private LocalDateTime date;
    private String status;
    private Category category;


    @Builder
    public PostResponse(int userId, String title, String description, String content, LocalDateTime date, String status, Category category) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.content = content;
        this.date = date;
        this.status = status;
        this.category = category;
    }
}
