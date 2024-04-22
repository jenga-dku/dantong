package org.jenga.dantong.infra.nhn.model;

public enum ReceiveType {
    MRT0("받는사람"),
    MRT1("참조"),
    MRT2("숨은참조");

    private String typeName;

    ReceiveType(String typeName) {
        this.typeName = typeName;
    }
}
