package org.jenga.dantong.post.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jenga.dantong.post.model.entity.Category;
import org.jenga.dantong.post.model.entity.Post;
import org.jenga.dantong.user.model.entity.User;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequest {

    private String title;
    private String description;
    private String content;
    private Category category;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;

    private boolean shown = true;

    public Post toEntity(User user) {
        return Post.builder()
            .user(user)
            .title(getTitle())
            .description(getDescription())
            .content(getContent())
            .category(getCategory())
            .startDate(getStartTime())
            .endDate(getEndTime())
            .build();
    }
}
