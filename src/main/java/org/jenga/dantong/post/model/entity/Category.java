package org.jenga.dantong.post.model.entity;


public enum Category {
    EVENT("행사"),
    NOTICE("공지"),
    PARTNERSHIP("제휴");

    private String categoryName;
    private int categoryCode;


    private Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
