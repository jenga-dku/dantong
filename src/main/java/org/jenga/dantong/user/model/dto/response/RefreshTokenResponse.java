package org.jenga.dantong.user.model.dto.response;

import lombok.Getter;
import org.jenga.dantong.global.auth.jwt.AuthenticationToken;

@Getter
public class RefreshTokenResponse {

    private final String accessToken;
    private final String refreshToken;

    public RefreshTokenResponse(AuthenticationToken token) {
        this.accessToken = token.getAccessToken();
        this.refreshToken = token.getRefreshToken();
    }
}

