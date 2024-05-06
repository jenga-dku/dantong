package org.jenga.dantong.post.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateRequest {
    private int userId;
    private String title;
    private String content;
    private String description;

    private LocalDateTime updateDate = LocalDateTime.now();

}
