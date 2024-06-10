package org.jenga.dantong.user.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jenga.dantong.user.model.entity.Major;
import org.jenga.dantong.user.model.entity.validator.Enum;

@Getter
@NoArgsConstructor
public class UserInfoEditRequest {

    @NotBlank(message = "이름은 필수 입력값입니다")
    private String name;

    @Enum(enumClass = Major.class, message = "전공을 선택해주세요")
    private Major major;

    @NotBlank(message = "전화번호는 필수 입력값입니다")
    @Pattern(regexp = "^010[0-9]{4}[0-9]{4}$", message = "전화번호를 정확히 입력해주세요")
    private String phoneNumber;
}
