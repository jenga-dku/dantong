package org.jenga.dantong.friend.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class AlreadySentRequestException extends ApplicationException {

    public AlreadySentRequestException() {
        super(HttpStatus.BAD_REQUEST, "ALREADY_SENT_REQUEST");
    }
}
