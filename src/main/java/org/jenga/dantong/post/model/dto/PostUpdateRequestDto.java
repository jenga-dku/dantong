package org.jenga.dantong.post.model.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.retry.annotation.Backoff;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostUpdateRequestDto {

    private int postId;

    private String title;
    private String content;
    private String description;

    private LocalDateTime updateDate = LocalDateTime.now();

//    @Builder
//    public PostUpdateDto(int postId, String title, String content, String description) {
//        this.postId = postId;
//        this.title = title;
//        this.content = content;
//        this.description = description;
//    }
}
