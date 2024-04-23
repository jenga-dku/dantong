package org.jenga.dantong.user.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class AlreadyUserExistException extends ApplicationException {

    public AlreadyUserExistException() {
        super(HttpStatus.BAD_REQUEST, "ALREADY_USER_EXIST");
    }
}
