package org.jenga.dantong.post.model.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostUpdateRequestDto {

    private int postId;

    private String title;
    private String content;
    private String description;

    private LocalDateTime updateDate = LocalDateTime.now();

}
