package com.sicredi.votacao.adapter.transportlayers.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.VoteInput;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.VoteResult;
import com.sicredi.votacao.internal.entities.Vote;
import com.sicredi.votacao.internal.interactors.votes.CreateVoteUseCase;
import com.sicredi.votacao.internal.interactors.votes.GetAllVotesUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.StreamUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(VoteControllerImpl.class)
public class VoteControllerTest {

    @Autowired
    WebTestClient webTestClient;
    @MockBean
    private GetAllVotesUseCase getAllVotesUseCase;
    @MockBean
    private CreateVoteUseCase createVoteUseCase;
    @Autowired
    private ObjectMapper objectMapper;

    @Value("classpath:datasource/sicredi/payload/vote.json")
    private Resource voteResource;

    @Test
    @DisplayName("Should return vote when create vote")
    void shouldReturnVoteWhenCreateVote() throws Exception {
        final var mockResultString = StreamUtils.copyToString(this.voteResource.getInputStream(), UTF_8);

        var mockObject = objectMapper.readValue(mockResultString, Vote.class);

        var mockEntryObject = objectMapper.readValue(mockResultString, VoteInput.class);

        when(this.createVoteUseCase.execute(any())).thenReturn(Mono.just(mockObject));

        this.webTestClient.post()
                .uri("/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(mockEntryObject), VoteInput.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(VoteResult.class);
    }

    @Test
    @DisplayName("Should return a vote's list when get all votes")
    void shouldReturnAVoteListWhenGetAllVotes() throws Exception {
        final var mockResultString = StreamUtils.copyToString(this.voteResource.getInputStream(), UTF_8);

        var mockObject = objectMapper.readValue(mockResultString, Vote.class);

        when(this.getAllVotesUseCase.execute()).thenReturn(Flux.just(mockObject));

        this.webTestClient.get()
                .uri("/votes")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(VoteResult.class);
    }
}
