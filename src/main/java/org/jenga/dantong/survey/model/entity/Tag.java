package org.jenga.dantong.survey.model.entity;


public enum Tag {
    SUBJECTIVE("주관식"),
    MULTIPLE("객관식");

    private String tagName;
    private int tagCode;

    private Tag(String tagName) {
        this.tagName = tagName;
    }
}
