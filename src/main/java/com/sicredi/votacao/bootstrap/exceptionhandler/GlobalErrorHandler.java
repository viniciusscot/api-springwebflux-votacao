package com.sicredi.votacao.bootstrap.exceptionhandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicredi.votacao.bootstrap.exceptions.EntityNotFoundException;
import com.sicredi.votacao.bootstrap.exceptions.VoteAlreadyComputedException;
import com.sicredi.votacao.bootstrap.exceptions.VoteNotAuthorizedException;
import com.sicredi.votacao.bootstrap.log.LogKibana;
import com.sicredi.votacao.bootstrap.log.LogLevel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Configuration
@Order(-2)
public class GlobalErrorHandler implements ErrorWebExceptionHandler {

    protected final Log logger = LogFactory.getLog(this.getClass());

    public static final String MSG_ERRO_GENERICA_USUARIO_FINAL
            = "An unexpected internal system error has occurred. Try again and if "
            + "the problem persists, contact your system administrator.";

    private ObjectMapper objectMapper;

    public GlobalErrorHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {

        if (throwable instanceof EntityNotFoundException)
            return handleEntityNotFound(serverWebExchange, throwable);
        if (throwable instanceof MethodNotAllowedException)
            return handleMethodNotAllowed(serverWebExchange, throwable);
        if (throwable instanceof VoteNotAuthorizedException)
            return handleVoteNotAuthorized(serverWebExchange, throwable);
        if (throwable instanceof VoteAlreadyComputedException)
            return handleVoteAlreadyComputed(serverWebExchange, throwable);

        return handleUncaught(serverWebExchange);
    }

    private void logError(ServerWebExchange serverWebExchange, Problem problem) {
        LogKibana log;
        var endpoint = serverWebExchange.getRequest().getPath().value();
        var methodHttp = serverWebExchange.getRequest().getMethod().name();

        log = new LogKibana(endpoint, methodHttp, LogLevel.ERROR, LocalDateTime.now(), Boolean.TRUE, problem.getDetail(), "", problem.toString());

        this.logger.error(log.toStringErrorCase());
    }

    private Mono<Void> handleMethodNotAllowed(ServerWebExchange serverWebExchange, Throwable throwable) {

        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
        String detail = throwable.getMessage();

        Problem problem = createProblem(status, problemType, detail)
                .setUserMessage(detail);

        return handleExceptionInternal(problem, status, serverWebExchange);
    }

    private Mono<Void> handleEntityNotFound(ServerWebExchange serverWebExchange, Throwable throwable) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.RESOURCE_NOT_FOUND;
        String detail = throwable.getMessage();

        Problem problem = createProblem(status, problemType, detail)
                .setUserMessage(detail);

        return handleExceptionInternal(problem, status, serverWebExchange);
    }

    private Mono<Void> handleVoteNotAuthorized(ServerWebExchange serverWebExchange, Throwable throwable) {

        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.VOTE_NOT_AUTHORIZED;
        String detail = throwable.getMessage();

        Problem problem = createProblem(status, problemType, detail)
                .setUserMessage(detail);

        return handleExceptionInternal(problem, status, serverWebExchange);
    }

    private Mono<Void> handleVoteAlreadyComputed(ServerWebExchange serverWebExchange, Throwable throwable) {

        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.VOTE_ALREADY_COMPUTED;
        String detail = throwable.getMessage();

        Problem problem = createProblem(status, problemType, detail)
                .setUserMessage(detail);

        return handleExceptionInternal(problem, status, serverWebExchange);
    }

    public Mono<Void> handleUncaught(ServerWebExchange serverWebExchange) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.SYSTEM_ERROR;
        String detail = MSG_ERRO_GENERICA_USUARIO_FINAL;

        Problem problem = createProblem(status, problemType, detail)
                .setUserMessage(detail);

        return handleExceptionInternal(problem, status, serverWebExchange);
    }

    @NotNull
    protected Mono<Void> handleExceptionInternal(Object body, @NotNull HttpStatus status, ServerWebExchange serverWebExchange) {

        if (body == null) {
            body = new Problem()
                    .setTimestamp(LocalDateTime.now())
                    .setTitle(status.getReasonPhrase())
                    .setStatus(status.value())
                    .setUserMessage(MSG_ERRO_GENERICA_USUARIO_FINAL);
        } else if (body instanceof String) {
            body = new Problem()
                    .setTimestamp(LocalDateTime.now())
                    .setTitle((String) body)
                    .setStatus(status.value())
                    .setUserMessage(MSG_ERRO_GENERICA_USUARIO_FINAL);
        }

        logError(serverWebExchange, (Problem) body);

        DataBufferFactory bufferFactory = serverWebExchange.getResponse().bufferFactory();

        serverWebExchange.getResponse().setStatusCode(status);
        DataBuffer dataBuffer = null;
        try {
            dataBuffer = bufferFactory.wrap(objectMapper.writeValueAsBytes(body));
        } catch (JsonProcessingException e) {
            dataBuffer = bufferFactory.wrap("".getBytes());
        }
        serverWebExchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return serverWebExchange.getResponse().writeWith(Mono.just(dataBuffer));
    }

    private Problem createProblem(HttpStatus status, ProblemType problemType, String detail) {

        return new Problem()
                .setTimestamp(LocalDateTime.now())
                .setStatus(status.value())
                .setType(problemType.getUri())
                .setTitle(problemType.getTitle())
                .setDetail(detail);
    }

}