package org.jenga.dantong.global.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "Dantong App", version = "v01"))
@SecurityScheme(
        name = "JWT Token",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI getOpenApi() {

        return new OpenAPI().components(new Components());
    }

    @Bean
    public GroupedOpenApi getUserApi() {

        return GroupedOpenApi
                .builder()
                .group("user & email")
                .pathsToMatch("/user/**")
                .build();

    }

    @Bean
    public GroupedOpenApi getSurveyApi() {

        return GroupedOpenApi
                .builder()
                .group("survey")
                .pathsToMatch("/survey/**")
                .build();

    }

    @Bean
    public GroupedOpenApi getPostApi() {

        return GroupedOpenApi
                .builder()
                .group("post")
                .pathsToMatch("/post/**")
                .build();

    }

    @Bean
    public GroupedOpenApi getAllApi() {

        return GroupedOpenApi
                .builder()
                .group("All")
                .pathsToMatch("/**")
                .build();

    }
}