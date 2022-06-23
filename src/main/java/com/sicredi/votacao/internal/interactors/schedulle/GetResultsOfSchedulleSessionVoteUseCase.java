package com.sicredi.votacao.internal.interactors.schedulle;

import com.sicredi.votacao.internal.entities.ResultOfSchedulle;
import com.sicredi.votacao.internal.entities.Session;
import com.sicredi.votacao.internal.interactors.session.GetSessionBySchedulleIdUseCase;
import com.sicredi.votacao.internal.interactors.votes.GetAllVotesBySessionIdUseCase;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class GetResultsOfSchedulleSessionVoteUseCase {

    private final GetSessionBySchedulleIdUseCase getSessionBySchedulleIdUseCase;
    private final GetAllVotesBySessionIdUseCase getAllVotesBySessionIdUseCase;

    public GetResultsOfSchedulleSessionVoteUseCase(GetSessionBySchedulleIdUseCase getSessionBySchedulleIdUseCase,
                                                   GetAllVotesBySessionIdUseCase getAllVotesBySessionIdUseCase) {
        this.getSessionBySchedulleIdUseCase = getSessionBySchedulleIdUseCase;
        this.getAllVotesBySessionIdUseCase = getAllVotesBySessionIdUseCase;
    }

    public Flux<ResultOfSchedulle> execute(final String schedulleId) {
        return this.getSessionBySchedulleIdUseCase.execute(schedulleId)
                .map(this::map);
    }

    private ResultOfSchedulle map(Session session) {
        final var votes = this.getAllVotesBySessionIdUseCase.execute(session.getId());

        final var votesYes = votes.filter(v -> v.getDecision().equals(true)).count().block();
        final var votesNo = votes.filter(v -> v.getDecision().equals(false)).count().block();

        return new ResultOfSchedulle()
                .setSession(session)
                .setVotes(votesYes + votesNo)
                .setVotesYes(votesYes)
                .setVotesNo(votesNo)
                .setResult(votesNo.equals(votesYes) ? "DRAW" : votesNo > votesYes ? "NO" : "YES");
    }
}
