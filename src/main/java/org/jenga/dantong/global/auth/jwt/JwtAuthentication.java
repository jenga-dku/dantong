package org.jenga.dantong.global.auth.jwt;

import java.util.ArrayList;
import java.util.Collection;
import lombok.AllArgsConstructor;
import org.jenga.dantong.user.model.entity.UserRole;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class JwtAuthentication implements AppAuthentication {

    private Long userId;
    private UserRole userRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for (String authority : userRole.getName().split(",")) {
            authorities.add(() -> authority);
        }
        return authorities;
    }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public UserRole getUserRole() {
        return userRole;
    }

    @Override
    public boolean isAdmin() {
        return userRole.isAdmin();
    }

    @Override
    public Object getCredentials() {
        return userId;
    }

    @Override
    public Object getDetails() {
        return userId;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    }

    @Override
    public String getName() {
        return null;
    }
}
