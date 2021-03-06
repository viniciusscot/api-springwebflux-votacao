package com.sicredi.votacao.internal.interactors.associate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicredi.votacao.internal.entities.Associate;
import com.sicredi.votacao.internal.repositories.AssociateRepository;
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

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class GetAllAssociatesUseCaseTest {

    private final AssociateRepository associateRepository;
    private final GetAllAssociatesUseCase getAllAssociatesUseCase;
    private final ObjectMapper objectMapper;

    @Value("classpath:datasource/sicredi/payload/associate.json")
    private Resource associateResource;

    @Autowired
    public GetAllAssociatesUseCaseTest(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.associateRepository = mock(AssociateRepository.class);
        this.getAllAssociatesUseCase = new GetAllAssociatesUseCase(this.associateRepository);
    }

    @Test
    @DisplayName("Should return associate's list when get all")
    void shouldReturnAssociateListWhenGetAll() throws Exception {
        final var mockResultString = StreamUtils.copyToString(this.associateResource.getInputStream(), UTF_8);

        var mockObject = objectMapper.readValue(mockResultString, Associate.class);

        when(this.associateRepository.getAll()).thenReturn(Flux.just(mockObject));

        var useCaseResponse = this.getAllAssociatesUseCase.execute();

        assertNotNull(useCaseResponse);
        useCaseResponse.count().subscribe(c -> assertThat(1L, equalTo(c)));
    }
}
