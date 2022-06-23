package com.sicredi.votacao.adapter.transportlayers.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.AssociateResult;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.SessionInput;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.SessionResult;
import com.sicredi.votacao.internal.entities.Session;
import com.sicredi.votacao.internal.interactors.session.OpenVoteSessionUseCase;
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
import reactor.core.publisher.Mono;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(SessionControllerImpl.class)
public class SessionControllerTest {
    @MockBean
    private OpenVoteSessionUseCase openVoteSessionUseCase;

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("classpath:datasource/sicredi/payload/session.json")
    private Resource sessionResource;

    @Test
    @DisplayName("Should return session when open vote session")
    void shouldReturnSessionWhenOpenVoteSession() throws Exception {
        final var mockResultString = StreamUtils.copyToString(this.sessionResource.getInputStream(), UTF_8);

        var mockObject = objectMapper.readValue(mockResultString, Session.class);

        var mockEntryObject = objectMapper.readValue(mockResultString, SessionInput.class);

        when(this.openVoteSessionUseCase.execute(any())).thenReturn(Mono.just(mockObject));

        this.webTestClient.post()
                .uri("/sessions")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(mockEntryObject), SessionInput.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(SessionResult.class);
    }
}
