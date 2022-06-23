package com.sicredi.votacao.internal.interactors.session;

import com.sicredi.votacao.bootstrap.exceptions.EntityInUseException;
import com.sicredi.votacao.bootstrap.utils.DateUtils;
import com.sicredi.votacao.internal.entities.Session;
import com.sicredi.votacao.internal.interactors.schedulle.GetSchedulleByIdUseCase;
import com.sicredi.votacao.internal.repositories.SessionRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.ZoneOffset;

@Service
public class OpenVoteSessionUseCase {

    private final SessionRepository sessionRepository;
    private final GetSchedulleByIdUseCase getSchedulleByIdUseCase;
    private final GetSessionBySchedulleIdAndStartDateAndEndDateUseCase getSessionBySchedulleIdAndStartDateAndEndDateUseCase;
    private final DateUtils dateUtils;

    public OpenVoteSessionUseCase(SessionRepository sessionRepository,
                                  GetSchedulleByIdUseCase getSchedulleByIdUseCase,
                                  GetSessionBySchedulleIdAndStartDateAndEndDateUseCase getSessionBySchedulleIdAndStartDateAndEndDateUseCase,
                                  DateUtils dateUtils) {
        this.sessionRepository = sessionRepository;
        this.getSchedulleByIdUseCase = getSchedulleByIdUseCase;
        this.getSessionBySchedulleIdAndStartDateAndEndDateUseCase = getSessionBySchedulleIdAndStartDateAndEndDateUseCase;
        this.dateUtils = dateUtils;
    }

    public Mono<Session> execute(Mono<Session> session) {
        final var now = this.dateUtils.getDate();

        return session
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(s -> {
                    this.getSchedulleByIdUseCase.execute(s.getSchedulleId()).block();

                    s.setStartDate(now.toInstant()
                                    .atOffset(ZoneOffset.UTC))
                            .setEndDate(this.dateUtils.addMinutesToDate(now, s.getDurationInMinutes())
                                    .toInstant()
                                    .atOffset(ZoneOffset.UTC));

                    if (this.getSessionBySchedulleIdAndStartDateAndEndDateUseCase.execute(s.getSchedulleId(), s.getStartDate()).blockOptional().isPresent())
                        throw new EntityInUseException("There is already an active session for this schedulle");
                })
                .flatMap(s -> this.sessionRepository.save(Mono.just(s)));
    }
}
