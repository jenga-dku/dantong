package org.jenga.dantong.user.model.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Major {
    SOFTWARE("소프트웨어학과"),
    COMPUTER("컴퓨터공학과"),
    STATICS("통계데이터사이언스학과"),
    MOBILE("모바일시스템공학과"),
    SECURE("사이버보안학과");

    @JsonValue
    private final String majorName;

    private Major(String majorName) {
        this.majorName = majorName;
    }

    @JsonCreator
    public static Major from(String val) {
        for (Major major : Major.values()) {
            if (major.name().equals(val)) {
                return major;
            }
        }
        return null;
    }
}
