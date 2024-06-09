package org.jenga.dantong.global.config;

import java.time.Clock;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.jenga.dantong.global.interceptor.SuccessResponseInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final SuccessResponseInterceptor successResponseInterceptor;

    @Bean
    public Clock clock() {
        return Clock.offset(Clock.systemUTC(), Duration.ofHours(9));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(successResponseInterceptor)
            .addPathPatterns("/**");
    }
}
