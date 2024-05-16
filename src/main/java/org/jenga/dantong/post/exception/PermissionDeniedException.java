package org.jenga.dantong.post.exception;

import org.jenga.dantong.global.error.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class PermissionDeniedException extends ApplicationException {

    public PermissionDeniedException() {
        super(HttpStatus.UNAUTHORIZED, "NO_PERMISSION_TO_ACCESS");
    }
}
