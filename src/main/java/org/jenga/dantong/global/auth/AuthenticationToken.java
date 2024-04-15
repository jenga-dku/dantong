package org.jenga.dantong.global.auth;

public interface AuthenticationToken {
    String getAccessToken();

    String getRefreshToken();
}
