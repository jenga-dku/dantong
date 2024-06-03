package org.jenga.dantong.post.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class PostNotFoundException extends ApplicationException {

    public PostNotFoundException() {
        super(HttpStatus.NOT_FOUND, "POST_NOT_FOUND");
    }
}
