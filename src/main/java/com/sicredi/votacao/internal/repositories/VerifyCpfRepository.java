package com.sicredi.votacao.internal.repositories;

import com.sicredi.votacao.internal.entities.VerifyCpf;
import reactor.core.publisher.Mono;

public interface VerifyCpfRepository {

    Mono<VerifyCpf> isValidAndCanVote(final String cpf);
}
