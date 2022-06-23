package com.sicredi.votacao.adapter.transportlayers.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.AssociateInput;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.AssociateResult;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.SchedulleInput;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.SchedulleResult;
import com.sicredi.votacao.internal.entities.ResultOfSchedulle;
import com.sicredi.votacao.internal.entities.Schedulle;
import com.sicredi.votacao.internal.interactors.schedulle.*;
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
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebFluxTest(SchedulleControllerImpl.class)
public class SchedulleControllerTest {

    @Autowired
    WebTestClient webTestClient;
    @MockBean
    private CreateSchedulleUseCase createSchedulleUseCase;
    @MockBean
    private DeleteSchedulleByIdUseCase deleteSchedulleByIdUseCase;
    @MockBean
    private GetSchedulleByIdUseCase getSchedulleByIdUseCase;
    @MockBean
    private GetAllSchedullesUseCase getAllSchedullesUseCase;
    @MockBean
    private GetResultsOfSchedulleSessionVoteUseCase getResultsOfSchedulleSessionVoteUseCase;
    @Autowired
    private ObjectMapper objectMapper;
    private static final String ANY_ASSOCIATE_ID = "anySchedulleId";

    @Value("classpath:datasource/sicredi/payload/schedulle.json")
    private Resource schedulleResource;

    @Value("classpath:datasource/sicredi/payload/results.json")
    private Resource resultsResource;

    @Test
    @DisplayName("Should return Schedulle when create schedulle")
    void shouldReturnSchedulleWhenCreateSchedulle() throws Exception {
        final var mockResultString = StreamUtils.copyToString(this.schedulleResource.getInputStream(), UTF_8);

        var mockObject = objectMapper.readValue(mockResultString, Schedulle.class);

        var mockEntryObject = objectMapper.readValue(mockResultString, SchedulleInput.class);

        when(this.createSchedulleUseCase.execute(any())).thenReturn(Mono.just(mockObject));

        this.webTestClient.post()
                .uri("/schedulles")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(mockEntryObject), SchedulleInput.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(SchedulleResult.class);
    }

    @Test
    @DisplayName("Should return no content Http 204 when delete schedulle")
    void shouldReturnNoContentHttp204WhenDeleteSchedulle() throws Exception {
        when(this.deleteSchedulleByIdUseCase.execute(anyString())).thenReturn(Mono.empty());

        this.webTestClient.delete()
                .uri(String.format("/schedulles/%s", ANY_ASSOCIATE_ID))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent();

    }

    @Test
    @DisplayName("Should return schedulle when get by id")
    void shouldReturnSchedulleWhenGetById() throws Exception {
        final var mockResultString = StreamUtils.copyToString(this.schedulleResource.getInputStream(), UTF_8);

        var mockObject = objectMapper.readValue(mockResultString, Schedulle.class);

        when(this.getSchedulleByIdUseCase.execute(anyString())).thenReturn(Mono.just(mockObject));

        this.webTestClient.get()
                .uri(String.format("/schedulles/%s", ANY_ASSOCIATE_ID))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(SchedulleResult.class);

    }

    @Test
    @DisplayName("Should return an schedulle's list when get all")
    void shouldReturnAnSchedulleListWhenGetAll() throws Exception {
        final var mockResultString = StreamUtils.copyToString(this.schedulleResource.getInputStream(), UTF_8);

        var mockObject = objectMapper.readValue(mockResultString, Schedulle.class);

        when(this.getAllSchedullesUseCase.execute()).thenReturn(Flux.just(mockObject));

        this.webTestClient.get()
                .uri("/schedulles")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(SchedulleResult.class);

    }
}
