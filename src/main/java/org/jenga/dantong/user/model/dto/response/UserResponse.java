package org.jenga.dantong.user.model.dto.response;

import lombok.Getter;
import org.jenga.dantong.user.model.entity.User;

@Getter
public class UserResponse {

    private String name;
    private String studentId;
    private String major;
    private String phoneNumber;
    private String userRole;

    public UserResponse(User user) {
        this.name = user.getName();
        this.studentId = user.getStudentId();
        this.major = user.getMajor().name();
        this.phoneNumber = user.getPhoneNumber();
        this.userRole = user.getUserRole().getName();
    }
}
