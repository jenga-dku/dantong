package org.jenga.dantong.post.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostSaveRequest {

    private int userId;
    private String title;
    private String description;
    private String content;

    private LocalDateTime date = LocalDateTime.now();
    private boolean shown = true;

    @Builder
    public PostSaveRequest(int userId, String title, String description, String content) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.content = content;
    }
}
