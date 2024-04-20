package org.jenga.dantong.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jenga.dantong.user.model.dto.EmailRequest;
import org.jenga.dantong.user.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user/email")
@RequiredArgsConstructor
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
}
