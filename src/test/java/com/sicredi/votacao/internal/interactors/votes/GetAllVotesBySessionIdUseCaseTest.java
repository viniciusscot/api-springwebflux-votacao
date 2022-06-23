package com.sicredi.votacao.internal.interactors.votes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicredi.votacao.internal.entities.Vote;
import com.sicredi.votacao.internal.repositories.VoteRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class GetAllVotesBySessionIdUseCaseTest {

    private final VoteRepository voteRepository;
    private final GetAllVotesBySessionIdUseCase getAllVotesBySessionIdUseCase;
    private final ObjectMapper objectMapper;

    @Value("classpath:datasource/sicredi/payload/vote.json")
    private Resource voteResource;

    @Autowired
    public GetAllVotesBySessionIdUseCaseTest(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.voteRepository = mock(VoteRepository.class);
        this.getAllVotesBySessionIdUseCase = new GetAllVotesBySessionIdUseCase(this.voteRepository);
    }

    @Test
    @DisplayName("Should return a vote's list when get all votes by session id")
    void shouldReturnAVoteListWhenGetAllVotesBySessionId() throws Exception {
        final var mockResultString = StreamUtils.copyToString(this.voteResource.getInputStream(), UTF_8);

        var mockObject = objectMapper.readValue(mockResultString, Vote.class);

        when(this.voteRepository.getAllBySessionId(anyString())).thenReturn(Flux.just(mockObject));

        var useCaseResponse = this.getAllVotesBySessionIdUseCase.execute(anyString());

        assertNotNull(useCaseResponse);
        useCaseResponse.count().subscribe(c -> assertThat(1L, equalTo(c)));

    }
}
