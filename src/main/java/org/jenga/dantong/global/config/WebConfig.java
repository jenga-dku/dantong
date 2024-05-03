package org.jenga.dantong.global.config;

import java.time.Clock;
import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final String corsList;

    public WebConfig(@Value("${app.cors}") String corsList) {
        this.corsList = corsList;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins(parseCorsList(corsList))
            .allowedHeaders("*")
            .allowedMethods("*");
    }

    private static String[] parseCorsList(String corsList) {
        String[] corsOrigins = corsList.split(",");
        for (int i = 0; i < corsOrigins.length; i++) {
            corsOrigins[i] = corsOrigins[i].strip();
        }
        return corsOrigins;
    }

    @Bean
    public Clock clock() {
        return Clock.offset(Clock.systemUTC(), Duration.ofHours(9));
    }
}
