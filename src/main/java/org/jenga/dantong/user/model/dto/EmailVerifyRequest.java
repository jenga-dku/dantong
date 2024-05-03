package org.jenga.dantong.user.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jenga.dantong.user.model.entity.Major;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailVerifyRequest {

    @NotBlank
    @Pattern(regexp = "^\\d{8}$", message = "학번을 입력해주세요")
    private final String studentId;

    @NotBlank
    private final String studentName;

    @NotEmpty
    private final Major major;

    @NotBlank
    @Pattern(regexp = "^.{6}$", message = "이메일 코드는 6자리 입니다.")
    private final String emailCode;


}
