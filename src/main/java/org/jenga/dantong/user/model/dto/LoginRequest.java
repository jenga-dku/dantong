package org.jenga.dantong.user.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class LoginRequest {

    @NotBlank
    private String studentId;

    @NotBlank
    private String password;
}
