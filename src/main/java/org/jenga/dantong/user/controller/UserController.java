package org.jenga.dantong.user.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jenga.dantong.global.auth.jwt.AppAuthentication;
import org.jenga.dantong.global.base.UserAuth;
import org.jenga.dantong.user.model.dto.LoginRequest;
import org.jenga.dantong.user.model.dto.LoginResponse;
import org.jenga.dantong.user.model.dto.SignupRequest;
import org.jenga.dantong.user.model.dto.UserResponse;
import org.jenga.dantong.user.model.entity.User;
import org.jenga.dantong.user.service.UserSignupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserSignupService userSignupService;

    @UserAuth
    @SecurityRequirement(name = "JWT Token")
    @GetMapping
    public UserResponse info(AppAuthentication authentication) {
        return userSignupService.userInfo(authentication.getUserId());
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = userSignupService.login(loginRequest);

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping(path = "/signup/{signup-token}")
    public void signup(@Valid @RequestBody SignupRequest request,
                       @PathVariable("signup-token") String signupToken) {
        User user = userSignupService.signup(request, signupToken);
    }
}
