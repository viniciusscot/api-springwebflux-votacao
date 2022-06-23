package com.sicredi.votacao.adapter.transportlayers.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.AssociateInput;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.AssociateResult;
import com.sicredi.votacao.internal.entities.Associate;
import com.sicredi.votacao.internal.interactors.associate.*;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebFluxTest(AssociateControllerImpl.class)
public class AssociateControllerTest {

    @Autowired
    WebTestClient webTestClient;
    @MockBean
    private CreateAssociateUseCase createAssociateUseCase;
    @MockBean
    private DeleteAssociateByIdUseCase deleteAssociateByIdUseCase;
    @MockBean
    private GetAssociateByIdUseCase getAssociateByIdUseCase;
    @MockBean
    private GetAllAssociatesUseCase getAllAssociatesUseCase;
    @MockBean
    private UpdateAssociateUseCase updateAssociateUseCase;
    @Autowired
    private ObjectMapper objectMapper;
    private static final String ANY_ASSOCIATE_ID = "anyAssociateId";

    @Value("classpath:datasource/sicredi/payload/associate.json")
    private Resource associateResource;


    @Test
    @DisplayName("Should return Associate when create associate")
    void shouldReturnAssociateWhenCreateAssociate() throws Exception {
        final var mockResultString = StreamUtils.copyToString(this.associateResource.getInputStream(), UTF_8);

        var mockObject = objectMapper.readValue(mockResultString, Associate.class);

        var mockEntryObject = objectMapper.readValue(mockResultString, AssociateInput.class);

        when(this.createAssociateUseCase.execute(any())).thenReturn(Mono.just(mockObject));

        this.webTestClient.post()
                .uri("/associates")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(mockEntryObject), AssociateInput.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(AssociateResult.class);
    }

    @Test
    @DisplayName("Should return no content Http 204 when delete associate")
    void shouldReturnNoContentHttp204WhenDeleteAssociate() {
        when(this.deleteAssociateByIdUseCase.execute(anyString())).thenReturn(Mono.empty());

        this.webTestClient.delete()
                .uri(String.format("/associates/%s", ANY_ASSOCIATE_ID))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNoContent();

    }

    @Test
    @DisplayName("Should return associate when get by id")
    void shouldReturnAssociateWhenGetById() throws Exception {
        final var mockResultString = StreamUtils.copyToString(this.associateResource.getInputStream(), UTF_8);

        var mockObject = objectMapper.readValue(mockResultString, Associate.class);

        when(this.getAssociateByIdUseCase.execute(anyString())).thenReturn(Mono.just(mockObject));

        this.webTestClient.get()
                .uri(String.format("/associates/%s", ANY_ASSOCIATE_ID))
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AssociateResult.class);

    }

    @Test
    @DisplayName("Should return an associate's list when get all")
    void shouldReturnAnAssociateListWhenGetAll() throws Exception {
        final var mockResultString = StreamUtils.copyToString(this.associateResource.getInputStream(), UTF_8);

        var mockObject = objectMapper.readValue(mockResultString, Associate.class);

        when(this.getAllAssociatesUseCase.execute()).thenReturn(Flux.just((mockObject)));

        this.webTestClient.get()
                .uri("/associates")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(AssociateResult.class);

    }

    @Test
    @DisplayName("Should return a associate when update by id")
    void shouldReturnAssociateWhenUpdateById() throws Exception {
        final var mockResultString = StreamUtils.copyToString(this.associateResource.getInputStream(), UTF_8);

        var mockObject = objectMapper.readValue(mockResultString, Associate.class);

        var mockEntryObject = objectMapper.readValue(mockResultString, AssociateInput.class);

        when(this.createAssociateUseCase.execute(any())).thenReturn(Mono.just(mockObject));

        this.webTestClient.put()
                .uri(String.format("/associates/%s", ANY_ASSOCIATE_ID))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(mockEntryObject), AssociateInput.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AssociateResult.class);

    }
}
