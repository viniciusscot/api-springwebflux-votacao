package com.sicredi.votacao.bootstrap.interceptor;

import com.sicredi.votacao.bootstrap.log.LogKibana;
import com.sicredi.votacao.bootstrap.log.LogLevel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
public class CustomWebFilter implements WebFilter {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange,
                             WebFilterChain webFilterChain) {
        BodyCaptureExchange bodyCaptureExchange = new BodyCaptureExchange(serverWebExchange);
        return webFilterChain.filter(bodyCaptureExchange).doOnSuccess((se) -> {

            LogKibana log;
            var endpoint = serverWebExchange.getRequest().getPath().value();
            var methodHttp = serverWebExchange.getRequest().getMethod().name();
            var bodyRequest = bodyCaptureExchange.getRequest().getFullBody();
            var bodyResponse = bodyCaptureExchange.getResponse().getFullBody();

            log = new LogKibana(endpoint, methodHttp, LogLevel.INFO, LocalDateTime.now(), Boolean.FALSE, "", bodyRequest, bodyResponse);

            this.logger.info(log.toStringSucefullCase());
        });
    }

}