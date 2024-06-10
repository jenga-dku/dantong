package org.jenga.dantong.survey.model.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SurveySubmitCreateRequest {

    @NotNull(message = "Survey Id는 필수 입력값입니다.")
    private Long surveyId;

    @NotNull
    @Valid
    private List<SurveyReplyCreateRequest> replyRequest;
}
