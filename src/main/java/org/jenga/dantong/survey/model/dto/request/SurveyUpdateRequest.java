package org.jenga.dantong.survey.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SurveyUpdateRequest {

    @NotBlank(message = "제목은 필수입력값입니다.")
    private String title;

    private String description;

    private Long postId;

    @NotNull(message = "시작 시각은 필수 입력값입니다.")
    @Future(message = "시작 시각은 현재 시각 이후입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startTime;

    @NotNull(message = "시작 시각은 필수 입력값입니다.")
    @Future(message = "시작 시각은 현재 시각 이후입니다.")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime endTime;

    @NotNull(message = "질문은 필수 입력값입니다.")
    @Valid
    @Builder.Default
    private List<SurveyItemUpdateRequest> surveyItems = new ArrayList<SurveyItemUpdateRequest>();

    public void itemOptionCheck() {
        surveyItems.forEach(SurveyItemUpdateRequest::tagCheck);
    }
}
