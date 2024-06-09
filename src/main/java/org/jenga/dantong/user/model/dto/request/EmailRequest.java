package org.jenga.dantong.user.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailRequest {

    @NotBlank
    @Pattern(regexp = "^\\d{8}$", message = "학번을 정확히 입력해주세요")
    private String studentId;

    public EmailRequest(String studentId) {
        this.studentId = studentId;
    }
}
