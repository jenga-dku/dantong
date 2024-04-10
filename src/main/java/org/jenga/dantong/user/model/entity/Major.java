package org.jenga.dantong.user.model.entity;

public enum Major {
    SOFTWARE("소프트웨어학과"),
    COMPUTER("컴퓨터공학과"),
    STATICS("통계데이터사이언스학과"),
    MOBILE("모바일시스템공학과"),
    SECURE("사이버보안학과");

    private String majorName;

    private Major(String majorName) {
        this.majorName = majorName;
    }
}
