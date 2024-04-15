package org.jenga.dantong.global.auth;

import org.jenga.dantong.user.model.entity.UserRole;
import org.springframework.security.core.Authentication;

public interface AppAuthentication extends Authentication {


    Long getUserId();

    UserRole getUserRole();

    boolean isAdmin();
}
