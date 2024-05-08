package org.jenga.dantong.user.model.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Setter
public class UserInfo {

    private String studentId;

    @JsonCreator
    public UserInfo(String studentId) {
        this.studentId = studentId;
    }
}
