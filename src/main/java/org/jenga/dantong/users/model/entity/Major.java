package org.jenga.dantong.users.model.entity;

public enum Major {
    SOFTWARE( "소프트웨어학과", 0),
    COMPUTER( "컴퓨터공학과", 1),
    STATICS("통계데이터사이언스학과", 2),
    MOBILE("모바일시스템공학과", 3),
    SECURE("사이버보안학과", 4)
    ;

    private String majorName;
    private int majorCode;

    private Major(String majorName, int majorCode) {
        this.majorName = majorName;
        this.majorCode = majorCode;
    }
}
