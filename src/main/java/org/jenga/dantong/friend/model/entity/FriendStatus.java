package org.jenga.dantong.friend.model.entity;

public enum FriendStatus {
    ACCEPT("수락"),
    WAITING("대기");

    private String status;

    private FriendStatus(String status) {
        this.status = status;
    }
}
