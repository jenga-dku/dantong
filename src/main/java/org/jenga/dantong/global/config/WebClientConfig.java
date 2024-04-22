package org.jenga.dantong.global.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
public class WebClientConfig {

    @Bean
    @Primary
    public WebClient plainWebClient() {
        HttpClient client = HttpClient.create(connectionProvider())
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
            .doOnConnected(conn -> conn.addHandlerFirst(new ReadTimeoutHandler(10))
                .addHandlerLast(new WriteTimeoutHandler(10)))
            .responseTimeout(Duration.ofSeconds(10));

        return WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(client))
            .build();
    }

    @Bean
    public ConnectionProvider connectionProvider() {
        return ConnectionProvider.builder("app-connection-provider")
            .maxIdleTime(Duration.ofSeconds(3))
            .evictInBackground(Duration.ofSeconds(30))
            .lifo()
            .build();
    }
}
