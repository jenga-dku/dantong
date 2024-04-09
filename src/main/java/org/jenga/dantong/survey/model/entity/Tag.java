package org.jenga.dantong.survey.model.entity;


public enum Tag {
    SUBJECTIVE("객관식", 0),
    MULTIPLE("주관식", 1)
    ;

    private String tagName;
    private int tagCode;

    private Tag(String tagName, int tagCode) {
        this.tagName = tagName;
        this.tagCode = tagCode;
    }
}
