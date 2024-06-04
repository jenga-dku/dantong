package org.jenga.dantong.post.model.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jenga.dantong.post.model.entity.Category;
import org.jenga.dantong.post.model.entity.Post;
import org.jenga.dantong.user.model.entity.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class PostCreateRequest {

    private String title;
    private String description;
    private String content;
    private Category category;
    private List<MultipartFile> imageFiles;

    @NotBlank(message = "시작 시각은 필수 입력값입니다")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;
    @NotBlank(message = "종료 시각은 필수 입력값입니다")
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
