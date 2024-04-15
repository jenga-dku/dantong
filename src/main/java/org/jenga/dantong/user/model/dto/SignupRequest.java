package org.jenga.dantong.user.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jenga.dantong.user.model.entity.Major;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SignupRequest {

    @NotBlank
    @Pattern(regexp = "^\\d{8}$", message = "학번을 정확히 입력해주세요")
    private String studentId;

    @NotBlank(message = "비밀번호는 필수 입력값입니다")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;
    private String name;
    private String phoneNumber;
    private Major major;

}
