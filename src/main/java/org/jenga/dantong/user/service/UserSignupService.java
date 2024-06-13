package org.jenga.dantong.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jenga.dantong.global.auth.jwt.AuthenticationToken;
import org.jenga.dantong.global.auth.jwt.JwtProvider;
import org.jenga.dantong.user.exception.AlreadyUserExistException;
import org.jenga.dantong.user.exception.UserNotFoundException;
import org.jenga.dantong.user.exception.WrongPasswordException;
import org.jenga.dantong.user.model.dto.UserInfo;
import org.jenga.dantong.user.model.dto.request.LoginRequest;
import org.jenga.dantong.user.model.dto.request.SignupRequest;
import org.jenga.dantong.user.model.dto.request.UserInfoEditRequest;
import org.jenga.dantong.user.model.dto.response.LoginResponse;
import org.jenga.dantong.user.model.dto.response.UserResponse;
import org.jenga.dantong.user.model.entity.Status;
import org.jenga.dantong.user.model.entity.User;
import org.jenga.dantong.user.model.entity.UserRole;
import org.jenga.dantong.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserSignupService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final EmailService emailService;
    private final AuthService authService;

    public User signup(SignupRequest dto, String signupToken) {
        UserInfo info = emailService.getStudentInfo(signupToken);

        checkUserExist(dto);
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());

        User user = User.builder()
            .studentId(info.getStudentId())
            .name(dto.getName())
            .major(dto.getMajor())
            .phoneNumber(dto.getPhoneNumber())
            .status(Status.INACTIVE)
            .password(encryptedPassword)
            .userRole(UserRole.USER)
            .build();

        deleteSignupAuths(signupToken);
        return userRepository.save(user);
    }

    private void deleteSignupAuths(String signupToken) {
        if (!authService.deleteStudentAuth(signupToken)) {
            log.error("Can't delete user signup authentication");
        }
    }

    @Transactional
    public LoginResponse login(LoginRequest dto) {
        User user = userRepository.findByStudentId(dto.getStudentId())
            .orElseThrow(UserNotFoundException::new);

        if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            AuthenticationToken token = jwtProvider.issue(user);
            userRepository.findByStudentId(dto.getStudentId());
            return new LoginResponse(token);
        } else {
            throw new WrongPasswordException();
        }
    }

    private void checkUserExist(SignupRequest dto) {
        if (userRepository.findByStudentId(dto.getStudentId()).isPresent()) {
            //TODO specific exception으로 변경능
            throw new AlreadyUserExistException();
        }
    }

    public UserResponse userInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        return new UserResponse(user);
    }

    @Transactional
    public void userInfoEdit(UserInfoEditRequest request, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        user.edit(request.getName(), request.getMajor(), request.getPhoneNumber());
    }
}
