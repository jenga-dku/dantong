package org.jenga.dantong.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jenga.dantong.global.auth.jwt.AuthenticationToken;
import org.jenga.dantong.global.auth.jwt.JwtProvider;
import org.jenga.dantong.user.model.dto.LoginRequest;
import org.jenga.dantong.user.model.dto.LoginResponse;
import org.jenga.dantong.user.model.dto.SignupRequest;
import org.jenga.dantong.user.model.entity.Status;
import org.jenga.dantong.user.model.entity.User;
import org.jenga.dantong.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserSignupService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public User signup(SignupRequest dto) {

        checkUserExist(dto);

        User user = User.builder()
            .studentId(dto.getStudentId())
            .name(dto.getName())
            .phoneNumber(dto.getPhoneNumber())
            .status(Status.INACTIVE)
            .password(passwordEncoder.encode(dto.getPassword()))
            .build();

        return userRepository.save(user);
    }

    public LoginResponse login(LoginRequest dto) {
        User user = userRepository.findByStudentId(dto.getStudentId()).orElseThrow(RuntimeException::new);

        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            AuthenticationToken token = jwtProvider.issue(user);
            userRepository.findByStudentId(dto.getStudentId());
            return new LoginResponse(token);
        } else {
            throw new RuntimeException();
        }
    }

    private void checkUserExist(SignupRequest dto) {
        if (userRepository.findByStudentId(dto.getStudentId()).isPresent()) {
            //TODO specific exception으로 변경
            throw new RuntimeException();
        }
    }

}
