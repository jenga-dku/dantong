package org.jenga.dantong.friend.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class FriendshipNotFoundException extends ApplicationException {

    public FriendshipNotFoundException() {
        super(HttpStatus.BAD_REQUEST, "FRIENDSHIP_NOT_FOUND");
    }
}
