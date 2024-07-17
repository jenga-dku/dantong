package org.jenga.dantong.friend.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class FriendAlreadySentRequestException extends ApplicationException {

    public FriendAlreadySentRequestException() {
        super(HttpStatus.BAD_REQUEST, "FRIEND_ALREADY_SENT_REQUEST");
    }
}
