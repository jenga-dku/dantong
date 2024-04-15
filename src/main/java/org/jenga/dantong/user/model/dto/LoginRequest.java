package org.jenga.dantong.user.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequest {
    @NotBlank
    private final String studentId;

    @NotBlank
    private final String password;
}
