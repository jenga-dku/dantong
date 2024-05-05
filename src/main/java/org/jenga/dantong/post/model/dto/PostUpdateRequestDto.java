package org.jenga.dantong.post.model.dto;


import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostUpdateRequestDto {

    private int postId;

    private String title;
    private String content;
    private String description;

    private LocalDateTime updateDate = LocalDateTime.now();

}
