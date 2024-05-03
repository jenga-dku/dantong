package org.jenga.dantong.global.auth.jwt;

public interface AuthenticationToken {
    String getAccessToken();

    String getRefreshToken();
}
