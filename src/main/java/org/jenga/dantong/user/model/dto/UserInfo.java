package org.jenga.dantong.user.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserInfo {

    private final String name;
    private final String studentId;
    private final String majorName;
}
