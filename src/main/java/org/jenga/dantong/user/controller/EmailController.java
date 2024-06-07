package org.jenga.dantong.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jenga.dantong.user.model.dto.UserInfo;
import org.jenga.dantong.user.model.dto.request.EmailRequest;
import org.jenga.dantong.user.model.dto.request.EmailVerifyRequest;
import org.jenga.dantong.user.model.dto.response.StudentVerifyResponse;
import org.jenga.dantong.user.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user/email")
@RequiredArgsConstructor
@RestController
public class EmailController {

    private final EmailService emailService;

    /**
     * 단국대학교 학생 이메일로 인증번호를 보냅니다.
     *
     * @param dto 요청 body (8자리 학번)
     */
    @PostMapping
    public void sendEmailCode(@Valid @RequestBody EmailRequest dto) {
        emailService.sendEmailCode(dto);
    }

    /**
     * 단국대학교 이메일 인증
     *
     * @param request
     * @return 회원가입용 토큰과 학생
     */
    @PostMapping("/verify")
    public StudentVerifyResponse verifyDKUEmail(@Valid @RequestBody EmailVerifyRequest request) {
        return emailService.validateEmailCode(request);
    }

    /**
     * 토큰을 통한 학생 정보 가져오기
     */
    @GetMapping("/{signup-token}")
    public UserInfo getStudentInfo(@PathVariable("signup-token") String signupToken) {
        return emailService.getStudentInfo(signupToken);
    }
}
