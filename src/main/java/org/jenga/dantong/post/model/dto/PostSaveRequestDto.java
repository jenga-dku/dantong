package org.jenga.dantong.post.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostSaveRequestDto {

    private int userId;
    private String title;
    private String description;
    private String content;

    private LocalDateTime date = LocalDateTime.now();
    private boolean shown = true;

    @Builder
    public PostSaveRequestDto(int userId, String title, String description, String content) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.content = content;
    }
}
