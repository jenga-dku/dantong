package org.jenga.dantong.user.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailRequest {

    @NotBlank
    @Pattern(regexp = "^\\d{8}$", message = "학번을 정확히 입력해주세요")
    private final String studentId;
}
