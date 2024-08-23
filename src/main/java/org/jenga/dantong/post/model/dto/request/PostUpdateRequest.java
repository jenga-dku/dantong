package org.jenga.dantong.post.model.dto.request;


import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jenga.dantong.post.model.entity.Category;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostUpdateRequest {


    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String title;

    private String content;
    private String description;

    @NotNull(message = "분류는 필수 입력값입니다.")
    private Category category;

    @NotNull(message = "시작 시각은 필수 입력값입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;

    @NotNull(message = "종료 시각은 필수 입력값입니다.")
    @Future(message = "종료 시각은 현재 시각 이후입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;

    private List<MultipartFile> imageFiles;

}
