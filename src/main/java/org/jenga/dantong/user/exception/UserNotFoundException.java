package org.jenga.dantong.user.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ApplicationException {

    public UserNotFoundException() {
        super(HttpStatus.NOT_FOUND, "USER_NOT_FOUND");
    }
}
