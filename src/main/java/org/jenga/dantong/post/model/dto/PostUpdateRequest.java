package org.jenga.dantong.post.model.dto;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jenga.dantong.post.model.entity.Category;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateRequest {

    private int postId;
    private int userId;
    private String title;
    private String content;
    private String description;
    private Category category;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime start_time;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime end_time;

    private LocalDateTime updateDate = LocalDateTime.now();

}
