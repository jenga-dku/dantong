package org.jenga.dantong.post.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostSaveDto {

    private int userId;
    private String title;
    private String description;
    private String content;

    private LocalDateTime date = LocalDateTime.now();
    private boolean shown = true;

    @Builder
    public PostSaveDto(int userId, String title, String description, String content) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.content = content;
    }
}
