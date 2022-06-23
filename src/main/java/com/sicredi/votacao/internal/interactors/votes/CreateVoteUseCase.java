package com.sicredi.votacao.internal.interactors.votes;

import com.sicredi.votacao.bootstrap.exceptions.SessionNotFoundException;
import com.sicredi.votacao.bootstrap.exceptions.VoteNotAuthorizedException;
import com.sicredi.votacao.bootstrap.utils.DateUtils;
import com.sicredi.votacao.internal.entities.Vote;
import com.sicredi.votacao.internal.interactors.associate.GetAssociateByIdUseCase;
import com.sicredi.votacao.internal.interactors.schedulle.GetSchedulleByIdUseCase;
import com.sicredi.votacao.internal.interactors.session.GetSessionBySchedulleIdAndStartDateAndEndDateUseCase;
import com.sicredi.votacao.internal.repositories.VoteRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.ZoneOffset;

@Service
public class CreateVoteUseCase {

    private final VoteRepository voteRepository;
    private final GetAssociateByIdUseCase getAssociateByIdUseCase;
    private final GetSessionBySchedulleIdAndStartDateAndEndDateUseCase getSessionBySchedulleIdAndStartDateAndEndDateUseCase;
    private final GetSchedulleByIdUseCase getSchedulleByIdUseCase;
    private final FindVoteByAssociateIdAndSchedulleIdThenTrownUseCase findVoteByAssociateIdAndSchedulleIdThenTrownUseCase;
    private final VerifyCpfUseCase verifyCpfUseCase;
    private final DateUtils dateUtils;

    public CreateVoteUseCase(VoteRepository voteRepository,
                             GetAssociateByIdUseCase getAssociateByIdUseCase,
                             GetSessionBySchedulleIdAndStartDateAndEndDateUseCase getSessionBySchedulleIdAndStartDateAndEndDateUseCase,
                             GetSchedulleByIdUseCase getSchedulleByIdUseCase,
                             FindVoteByAssociateIdAndSchedulleIdThenTrownUseCase findVoteByAssociateIdAndSchedulleIdThenTrownUseCase,
                             VerifyCpfUseCase verifyCpfUseCase,
                             DateUtils dateUtils) {
        this.voteRepository = voteRepository;
        this.getAssociateByIdUseCase = getAssociateByIdUseCase;
        this.getSessionBySchedulleIdAndStartDateAndEndDateUseCase = getSessionBySchedulleIdAndStartDateAndEndDateUseCase;
        this.getSchedulleByIdUseCase = getSchedulleByIdUseCase;
        this.findVoteByAssociateIdAndSchedulleIdThenTrownUseCase = findVoteByAssociateIdAndSchedulleIdThenTrownUseCase;
        this.verifyCpfUseCase = verifyCpfUseCase;
        this.dateUtils = dateUtils;
    }

    public Mono<Vote> execute(Mono<Vote> vote) {
        return vote
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(v -> {
                    final var now = dateUtils.getDate().toInstant().atOffset(ZoneOffset.UTC);
                    final var associate = this.getAssociateByIdUseCase.execute(v.getAssociateId()).block();
                    final var schedulle = this.getSchedulleByIdUseCase.execute(v.getSchedulleId()).block();
                    final var session = this.getSessionBySchedulleIdAndStartDateAndEndDateUseCase.execute(schedulle.getId(), now).blockOptional();
                    if (session.isEmpty())
                        throw new SessionNotFoundException(v.getSchedulleId());

                    this.findVoteByAssociateIdAndSchedulleIdThenTrownUseCase.execute(v.getAssociateId(), schedulle.getId()).block();

                    if (Boolean.FALSE.equals(this.verifyCpfUseCase.execute(associate.getCpf()).block()))
                        throw new VoteNotAuthorizedException(String.format("Associate with cpf %s is not authorized to vote.", associate.getCpf()));

                    v.setHorary(now)
                            .setSessionId(session.get().getId());
                })
                .flatMap(v -> this.voteRepository.save(Mono.just(v)));
    }

}