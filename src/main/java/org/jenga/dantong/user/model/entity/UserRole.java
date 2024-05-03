package org.jenga.dantong.user.model.entity;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import org.jenga.dantong.global.auth.jwt.AppAuthentication;

@Getter
public enum UserRole {
    SER("ROLE_USER"),
    GUEST("ROLE_GUEST"),
    ADMIN("ROLE_USER, ROLE_ADMIN");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }

    private static final Map<String, UserRole> BY_LABEL =
        Stream.of(values()).collect(Collectors.toMap(UserRole::getName, e -> e));

    public static UserRole of(String name) {
        return BY_LABEL.get(name);
    }

    public static UserRole from(AppAuthentication auth) {
        if (auth == null) {
            return GUEST;
        }
        return auth.getUserRole();
    }

    public boolean isAdmin() {
        return this == ADMIN;
    }
}
