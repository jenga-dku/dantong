package org.jenga.dantong.global.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface AuthenticationProvider {

    String getAccessTokenFromHeader(HttpServletRequest request);

    Authentication getAuthentication(String accessToken);

}

