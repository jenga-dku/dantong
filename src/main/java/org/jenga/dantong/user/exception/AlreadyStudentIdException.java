package org.jenga.dantong.user.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class AlreadyStudentIdException extends ApplicationException {

    public AlreadyStudentIdException() {
        super(HttpStatus.BAD_REQUEST, "ALREADY_STUDENT_ID");
    }
}
