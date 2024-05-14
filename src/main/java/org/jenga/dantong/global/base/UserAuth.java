package org.jenga.dantong.global.base;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.jenga.dantong.global.auth.jwt.JwtProvider;
import org.springframework.security.access.annotation.Secured;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SecurityRequirement(name = JwtProvider.AUTHORIZATION)
@Secured("ROLE_USER")
public @interface UserAuth {

}
