package org.jenga.dantong.survey.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jenga.dantong.survey.model.entity.Tag;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SurveyItemCreateRequest {

    @NotNull(message = "제목은 필수 입력값입니다.")
    @NotEmpty(message = "제목은 필수 입력값입니다.")
    @NotBlank(message = "제목은 공백일 수 없습니다.")
    private String title;

    @NotNull(message = "질문 유형은 필수 입력값입니다.")
    private Tag tag;

    private List<String> options;

    private String description;
}