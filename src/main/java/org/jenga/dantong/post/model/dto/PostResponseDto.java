package org.jenga.dantong.post.model.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostResponseDto {
//    private int postId;
    private int userId;
    private String title;
    private String description;
    private String content;
    private LocalDateTime date;

    @Builder
    public PostResponseDto(int userId, String title, String description, String content, LocalDateTime date) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.content = content;
        this.date = date;
    }
}
