package org.jenga.dantong.survey.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class SurveyCreateRequest {

    @NotNull(message = "제목은 필수 입력값입니다.")
    @NotEmpty(message = "제목은 필수 입력값입니다.")
    @NotBlank(message = "제목은 공백일 수 없습니다.")
    private String title;

    private String description;

    @NotNull(message = "Post Id는 필수 입력값입니다.")
    private Long postId;

    @NotNull(message = "시작 시각은 필수 입력값입니다.")
    @Future(message = "시작 시각은 현재 시각 이후입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;

    @NotNull(message = "종료 시각은 필수 입력값입니다.")
    @Future(message = "종료 시각은 현재 시각 이후입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;

    @NotNull(message = "질문은 필수 입력값입니다.")
    @Valid
    private List<SurveyItemCreateRequest> surveyItems = new ArrayList<SurveyItemCreateRequest>();
}
