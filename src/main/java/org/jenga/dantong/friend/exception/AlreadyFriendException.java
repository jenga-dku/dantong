package org.jenga.dantong.friend.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class AlreadyFriendException extends ApplicationException {
    public AlreadyFriendException() {
        super(HttpStatus.BAD_REQUEST, "ALREADY_FRIEND");
    }
}
