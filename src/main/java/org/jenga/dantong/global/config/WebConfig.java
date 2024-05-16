package org.jenga.dantong.global.config;

import java.time.Clock;
import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Bean
    public Clock clock() {
        return Clock.offset(Clock.systemUTC(), Duration.ofHours(9));
    }
}
