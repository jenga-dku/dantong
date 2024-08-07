package org.jenga.dantong.user.model.dto.response;

public class StudentInfo {

    private String name;
    private String studentId;
    private String majorName;
    private String departmentName;

    public StudentInfo(String name, String studentId, String majorName, String departmentName) {
        this.name = name;
        this.studentId = studentId;
        this.majorName = majorName;
        this.departmentName = departmentName;
    }


}
