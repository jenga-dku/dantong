package org.jenga.dantong.user.model.dto.response;


import lombok.Getter;
import org.jenga.dantong.user.model.dto.UserInfo;

@Getter
public class StudentVerifyResponse {

    private final String studentId;
    private final String signupToken;

    public StudentVerifyResponse(UserInfo info, String signupToken) {
        this.studentId = info.getStudentId();
        this.signupToken = signupToken;
    }
}
