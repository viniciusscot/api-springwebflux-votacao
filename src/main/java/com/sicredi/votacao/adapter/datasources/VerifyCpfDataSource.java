package com.sicredi.votacao.adapter.datasources;

import com.sicredi.votacao.bootstrap.exceptions.VoteNotAuthorizedException;
import com.sicredi.votacao.internal.entities.VerifyCpf;
import com.sicredi.votacao.internal.repositories.VerifyCpfRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class VerifyCpfDataSource implements VerifyCpfRepository {

    private final WebClient verifyCpfGateway;

    public VerifyCpfDataSource(WebClient verifyCpfGateway) {
        this.verifyCpfGateway = verifyCpfGateway;
    }

    @Override
    public Mono<VerifyCpf> isValidAndCanVote(String cpf) {
        return verifyCpfGateway.get()
                .uri("/users/".concat(cpf))
                .retrieve()
                .bodyToMono(VerifyCpf.class)
                .onErrorMap(throwable -> new VoteNotAuthorizedException(String.format("The cpf of number %s is invalid", cpf)));

    }
}
