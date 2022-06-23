package com.sicredi.votacao.adapter.datasources.services;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.springframework.web.reactive.function.client.WebClient.Builder;

@Configuration
public class WebClientVerifyCpf {

    private static final Logger logger = LoggerFactory.getLogger(WebClientVerifyCpf.class);

    @Value("${services.sicredi.cpfGateway}")
    String verificaCpfBaseUrl;

    HttpClient httpClient;

    private Integer TIMEOUT_MILLIS = 30000;

    public WebClientVerifyCpf() {
        httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT_MILLIS)
                .responseTimeout(Duration.ofMillis(TIMEOUT_MILLIS))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)));
    }

    @Bean
    public WebClient verifyCpfGateway(Builder builder) {
        return builder
                .baseUrl(verificaCpfBaseUrl)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(logRequest())
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return (clientRequest, next) -> {
            logger.info("Request: {} {}", clientRequest.method(), clientRequest.url());

            return next.exchange(clientRequest);
        };
    }
}
