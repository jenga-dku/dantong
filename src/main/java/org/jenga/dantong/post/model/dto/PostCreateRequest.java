package org.jenga.dantong.post.model.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jenga.dantong.post.model.entity.Category;
import org.jenga.dantong.post.model.entity.Post;
import org.jenga.dantong.user.model.entity.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostCreateRequest {

    @NotNull(message = "제목은 필수 입력값입니다.")
    @NotEmpty(message = "제목은 필수 입력값입니다.")
    @NotBlank(message = "제목은 공백일 수 없습니다.")
    private String title;

    private String description;

    private String content;

    @NotNull(message = "분류는 필수 입력값입니다.")
    private Category category;

    private List<MultipartFile> imageFiles;

    @NotNull(message = "시작 시각은 필수 입력값입니다.")
    @Future(message = "시작 시각은 현재 시각 이후입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;

    @NotNull(message = "종료 시각은 필수 입력값입니다.")
    @Future(message = "종료 시각은 현재 시각 이후입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;

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
