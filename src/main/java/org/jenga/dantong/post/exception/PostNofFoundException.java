package org.jenga.dantong.post.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class PostNofFoundException extends ApplicationException {

    public PostNofFoundException() {
        super(HttpStatus.NOT_FOUND, "POST_NOT_FOUND");
    }
}
