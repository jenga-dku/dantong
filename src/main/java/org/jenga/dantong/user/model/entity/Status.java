package org.jenga.dantong.user.model.entity;

public enum Status {
    /**
     * 활성화 계정
     */
    ACTIVE,

    /**
     * 비활성 계정
     */
    INACTIVE;

    public boolean isActive() {
        return this == ACTIVE;
    }
}
