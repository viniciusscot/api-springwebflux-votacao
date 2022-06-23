package com.sicredi.votacao.internal.interactors.votes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicredi.votacao.bootstrap.exceptions.SessionNotFoundException;
import com.sicredi.votacao.bootstrap.exceptions.VoteNotAuthorizedException;
import com.sicredi.votacao.bootstrap.utils.DateUtils;
import com.sicredi.votacao.internal.entities.Associate;
import com.sicredi.votacao.internal.entities.Schedulle;
import com.sicredi.votacao.internal.entities.Session;
import com.sicredi.votacao.internal.entities.Vote;
import com.sicredi.votacao.internal.interactors.associate.GetAssociateByIdUseCase;
import com.sicredi.votacao.internal.interactors.schedulle.GetSchedulleByIdUseCase;
import com.sicredi.votacao.internal.interactors.session.GetSessionBySchedulleIdAndStartDateAndEndDateUseCase;
import com.sicredi.votacao.internal.repositories.VoteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import reactor.core.publisher.Mono;

import java.util.Calendar;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CreateVoteUseCaseTest {

    private final CreateVoteUseCase createVoteUseCase;
    private final VoteRepository voteRepository;
    private final GetAssociateByIdUseCase getAssociateByIdUseCase;
    private final GetSessionBySchedulleIdAndStartDateAndEndDateUseCase getSessionBySchedulleIdAndStartDateAndEndDateUseCase;
    private final GetSchedulleByIdUseCase getSchedulleByIdUseCase;
    private final FindVoteByAssociateIdAndSchedulleIdThenTrownUseCase findVoteByAssociateIdAndSchedulleIdThenTrownUseCase;
    private final VerifyCpfUseCase verifyCpfUseCase;
    private final DateUtils dateUtils;
    private final ObjectMapper objectMapper;

    @Value("classpath:datasource/sicredi/payload/session.json")
    private Resource sessionResource;

    @Value("classpath:datasource/sicredi/payload/schedulle.json")
    private Resource schedulleResource;

    @Value("classpath:datasource/sicredi/payload/associate.json")
    private Resource associateResource;

    @Value("classpath:datasource/sicredi/payload/vote.json")
    private Resource voteResource;

    @Autowired
    public CreateVoteUseCaseTest(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.voteRepository = mock(VoteRepository.class);
        this.getAssociateByIdUseCase = mock(GetAssociateByIdUseCase.class);
        this.getSessionBySchedulleIdAndStartDateAndEndDateUseCase = mock(GetSessionBySchedulleIdAndStartDateAndEndDateUseCase.class);
        this.getSchedulleByIdUseCase = mock(GetSchedulleByIdUseCase.class);
        this.findVoteByAssociateIdAndSchedulleIdThenTrownUseCase = mock(FindVoteByAssociateIdAndSchedulleIdThenTrownUseCase.class);
        this.verifyCpfUseCase = mock(VerifyCpfUseCase.class);
        this.dateUtils = mock(DateUtils.class);
        this.createVoteUseCase = new CreateVoteUseCase(this.voteRepository, this.getAssociateByIdUseCase,
                this.getSessionBySchedulleIdAndStartDateAndEndDateUseCase, this.getSchedulleByIdUseCase,
                this.findVoteByAssociateIdAndSchedulleIdThenTrownUseCase, this.verifyCpfUseCase, this.dateUtils);
    }

    @Test
    @DisplayName("Should return session when open session vote")
    void shouldReturnVoteWhenCreateVote() throws Exception {
        final var mockSessionString = StreamUtils.copyToString(this.sessionResource.getInputStream(), UTF_8);
        var mockSession = objectMapper.readValue(mockSessionString, Session.class);
        final var mockSchedulleString = StreamUtils.copyToString(this.schedulleResource.getInputStream(), UTF_8);
        var mockSchedulle = objectMapper.readValue(mockSchedulleString, Schedulle.class);
        final var mockAssociateString = StreamUtils.copyToString(this.associateResource.getInputStream(), UTF_8);
        var mockAssociate = objectMapper.readValue(mockAssociateString, Associate.class);
        final var mockVoteString = StreamUtils.copyToString(this.voteResource.getInputStream(), UTF_8);
        var mockVote = objectMapper.readValue(mockVoteString, Vote.class);

        when(this.dateUtils.getDate()).thenReturn(Calendar.getInstance().getTime());
        when(this.getAssociateByIdUseCase.execute(anyString())).thenReturn(Mono.just(mockAssociate));
        when(this.getSchedulleByIdUseCase.execute(anyString())).thenReturn(Mono.just(mockSchedulle));
        when(this.getSessionBySchedulleIdAndStartDateAndEndDateUseCase.execute(anyString(), any())).thenReturn(Mono.just(mockSession));
        when(this.findVoteByAssociateIdAndSchedulleIdThenTrownUseCase.execute(anyString(), anyString())).thenReturn(Mono.empty());
        when(this.verifyCpfUseCase.execute(anyString())).thenReturn(Mono.just(Boolean.TRUE));
        when(this.voteRepository.save(any())).thenReturn(Mono.just(mockVote));

        var useCaseResponse = this.createVoteUseCase.execute(Mono.just(mockVote)).block();

        assertNotNull(useCaseResponse);
        assertThat(mockVote.getId(), equalTo(useCaseResponse.getId()));
    }

    @Test
    @DisplayName("Should throw exception when try create vote but session is not found")
    void shouldThrowExceptionWhenTryCreateVoteButSessionIsNotFound() throws Exception {
        final var mockSchedulleString = StreamUtils.copyToString(this.schedulleResource.getInputStream(), UTF_8);
        var mockSchedulle = objectMapper.readValue(mockSchedulleString, Schedulle.class);
        final var mockAssociateString = StreamUtils.copyToString(this.associateResource.getInputStream(), UTF_8);
        var mockAssociate = objectMapper.readValue(mockAssociateString, Associate.class);
        final var mockVoteString = StreamUtils.copyToString(this.voteResource.getInputStream(), UTF_8);
        var mockVote = objectMapper.readValue(mockVoteString, Vote.class);

        when(this.dateUtils.getDate()).thenReturn(Calendar.getInstance().getTime());
        when(this.getAssociateByIdUseCase.execute(anyString())).thenReturn(Mono.just(mockAssociate));
        when(this.getSchedulleByIdUseCase.execute(anyString())).thenReturn(Mono.just(mockSchedulle));
        when(this.getSessionBySchedulleIdAndStartDateAndEndDateUseCase.execute(anyString(), any())).thenReturn(Mono.empty());

        assertThrows(SessionNotFoundException.class, () -> this.createVoteUseCase.execute(Mono.just(mockVote)).block());
    }

    @Test
    @DisplayName("Should throw exception when try create vote but session is not found")
    void shouldThrowExceptionWhenTryCreateVoteButCpfIsNotAuthorized() throws Exception {
        final var mockSessionString = StreamUtils.copyToString(this.sessionResource.getInputStream(), UTF_8);
        var mockSession = objectMapper.readValue(mockSessionString, Session.class);
        final var mockSchedulleString = StreamUtils.copyToString(this.schedulleResource.getInputStream(), UTF_8);
        var mockSchedulle = objectMapper.readValue(mockSchedulleString, Schedulle.class);
        final var mockAssociateString = StreamUtils.copyToString(this.associateResource.getInputStream(), UTF_8);
        var mockAssociate = objectMapper.readValue(mockAssociateString, Associate.class);
        final var mockVoteString = StreamUtils.copyToString(this.voteResource.getInputStream(), UTF_8);
        var mockVote = objectMapper.readValue(mockVoteString, Vote.class);

        when(this.dateUtils.getDate()).thenReturn(Calendar.getInstance().getTime());
        when(this.getAssociateByIdUseCase.execute(anyString())).thenReturn(Mono.just(mockAssociate));
        when(this.getSchedulleByIdUseCase.execute(anyString())).thenReturn(Mono.just(mockSchedulle));
        when(this.getSessionBySchedulleIdAndStartDateAndEndDateUseCase.execute(anyString(), any())).thenReturn(Mono.just(mockSession));
        when(this.findVoteByAssociateIdAndSchedulleIdThenTrownUseCase.execute(anyString(), anyString())).thenReturn(Mono.empty());
        when(this.verifyCpfUseCase.execute(anyString())).thenReturn(Mono.just(Boolean.FALSE));

        assertThrows(VoteNotAuthorizedException.class, () -> this.createVoteUseCase.execute(Mono.just(mockVote)).block());
    }
}
