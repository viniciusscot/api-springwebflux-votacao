package com.sicredi.votacao.internal.interactors.votes;

import com.sicredi.votacao.internal.entities.VerifyCpf;
import com.sicredi.votacao.internal.repositories.VerifyCpfRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
public class VerifyCpfUseCase {

    private final VerifyCpfRepository verifyCpfRepository;


    public VerifyCpfUseCase(VerifyCpfRepository verifyCpfRepository) {
        this.verifyCpfRepository = verifyCpfRepository;
    }

    public Mono<Boolean> execute(final String cpf) {
        return this.verifyCpfRepository.isValidAndCanVote(cpf)
                .map(a -> {
                    System.out.println(a.getStatus().toString());
                    if (a.getStatus().equals(VerifyCpf.StatusEnum.ABLE_TO_VOTE))
                        return TRUE;

                    return FALSE;
                });
    }
}
