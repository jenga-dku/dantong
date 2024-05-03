package org.jenga.dantong.user.model.dto;


import lombok.Getter;

@Getter
public class StudentVerifyResponse {

    private final String studentName;

    private final String studentId;
    private final String major;
    private final String signupToken;

    public StudentVerifyResponse(UserInfo info, String signupToken) {
        this.studentName = info.getName();
        this.studentId = info.getStudentId();
        this.major = info.getMajorName();
        this.signupToken = signupToken;
    }
}
