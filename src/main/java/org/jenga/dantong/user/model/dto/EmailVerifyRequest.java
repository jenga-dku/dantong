package org.jenga.dantong.user.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailVerifyRequest {

    @NotBlank
    @Pattern(regexp = "^\\d{8}$", message = "학번을 입력해주세요")
    private String studentId;

    @NotBlank
    @Pattern(regexp = "^.{6}$", message = "이메일 코드는 6자리 입니다.")
    private String emailCode;


}
