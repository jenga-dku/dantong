package org.jenga.dantong.survey.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SurveyReplyUpdateRequest {

    @NotNull(message = "Survey Item Id는 필수 입력값입니다.")
    private Long surveyItemId;

    @NotNull(message = "답변은 필수 입력값입니다.")
    @NotBlank(message = "답변은 공백일 수 없습니다.")
    private String content;
}
