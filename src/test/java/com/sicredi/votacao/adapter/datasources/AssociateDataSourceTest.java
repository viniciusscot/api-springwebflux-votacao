package com.sicredi.votacao.adapter.datasources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicredi.votacao.adapter.datasources.services.MongoAssociateRepository;
import com.sicredi.votacao.adapter.datasources.services.model.AssociateModel;
import com.sicredi.votacao.bootstrap.exceptions.AssociateNotFoundException;
import com.sicredi.votacao.internal.entities.Associate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class AssociateDataSourceTest {

    private final MongoAssociateRepository mongoAssociateRepository;
    private final AssociateDataSource associateDataSource;
    private final ObjectMapper objectMapper;

    @Value("classpath:datasource/sicredi/payload/associate.json")
    private Resource associateResource;

    @Autowired
    public AssociateDataSourceTest(ObjectMapper objectMapper) {
        this.mongoAssociateRepository = Mockito.mock(MongoAssociateRepository.class);
        this.associateDataSource = new AssociateDataSource(this.mongoAssociateRepository);
        this.objectMapper = objectMapper;
    }

    @Test
    @DisplayName("Should Return Associate When Save Associate")
    void shouldReturnAssociateWhenSave() throws Exception {
        final var mockResultString = StreamUtils.copyToString(this.associateResource.getInputStream(), UTF_8);

        var mockObject = objectMapper.readValue(mockResultString, AssociateModel.class);

        var mockEntryObject = objectMapper.readValue(mockResultString, Associate.class);

        when(this.mongoAssociateRepository.save(any())).thenReturn(Mono.just(mockObject));

        var dataSourceResponse = this.associateDataSource.save(Mono.just(mockEntryObject)).block();

        assertNotNull(dataSourceResponse);
        assertThat(mockObject.getId(), equalTo(dataSourceResponse.getId()));

    }

    @Test
    @DisplayName("Should return void when delete associate")
    void shouldReturnVoidWhenDeleteAssociate() {
        when(this.mongoAssociateRepository.deleteById(anyString())).thenReturn(Mono.empty());

        assertDoesNotThrow(() -> this.associateDataSource.delete(anyString()));

    }

    @Test
    @DisplayName("Should return associate when get by id")
    void shouldReturnAssociateWhenGetById() throws Exception {
        final var mockResultString = StreamUtils.copyToString(this.associateResource.getInputStream(), UTF_8);

        var mockObject = objectMapper.readValue(mockResultString, AssociateModel.class);

        when(this.mongoAssociateRepository.findById(anyString())).thenReturn(Mono.just(mockObject));

        var dataSourceResponse = this.associateDataSource.get(mockObject.getId()).block();

        assertNotNull(dataSourceResponse);
        assertThat(mockObject.getId(), equalTo(dataSourceResponse.getId()));
    }

    @Test
    @DisplayName("Should throw exception when get by non-existing if")
    void shouldThrowExceptionWhenGetByNonExistingId() {
        when(this.mongoAssociateRepository.findById(anyString())).thenReturn(Mono.empty());

        assertThrows(AssociateNotFoundException.class, () -> this.associateDataSource.get(anyString()).block());
    }

    @Test
    @DisplayName("Should return a associate list when get all")
    void shouldReturnAssociateListWhenGetAll() throws Exception {
        final var mockResultString = StreamUtils.copyToString(this.associateResource.getInputStream(), UTF_8);

        var mockObject = objectMapper.readValue(mockResultString, AssociateModel.class);

        when(this.mongoAssociateRepository.findAll()).thenReturn(Flux.just(mockObject));

        var dataSourceResponse = this.associateDataSource.getAll();

        assertNotNull(dataSourceResponse);

        dataSourceResponse.count().subscribe(c -> assertThat(1L, equalTo(c)));

    }
}
