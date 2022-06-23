package com.sicredi.votacao.adapter.transportlayers.restapi;

import com.sicredi.votacao.adapter.transportlayers.mapper.AssociateMapper;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.AssociateInput;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.AssociateResult;
import com.sicredi.votacao.adapter.transportlayers.restapi.openapi.AssociatesApi;
import com.sicredi.votacao.internal.interactors.associate.*;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/")
@Api(tags = "Associate")
public class AssociateControllerImpl implements AssociatesApi {

    private final CreateAssociateUseCase createAssociateUseCase;
    private final DeleteAssociateByIdUseCase deleteAssociateByIdUseCase;
    private final GetAssociateByIdUseCase getAssociateByIdUseCase;
    private final GetAllAssociatesUseCase getAllAssociatesUseCase;
    private final UpdateAssociateUseCase updateAssociateUseCase;

    public AssociateControllerImpl(CreateAssociateUseCase createAssociateUseCase,
                                   DeleteAssociateByIdUseCase deleteAssociateByIdUseCase,
                                   GetAssociateByIdUseCase getAssociateByIdUseCase,
                                   GetAllAssociatesUseCase getAllAssociatesUseCase,
                                   UpdateAssociateUseCase updateAssociateUseCase) {
        this.createAssociateUseCase = createAssociateUseCase;
        this.deleteAssociateByIdUseCase = deleteAssociateByIdUseCase;
        this.getAssociateByIdUseCase = getAssociateByIdUseCase;
        this.getAllAssociatesUseCase = getAllAssociatesUseCase;
        this.updateAssociateUseCase = updateAssociateUseCase;
    }

    @Override
    public Mono<ResponseEntity<AssociateResult>> createAssociate(final Mono<AssociateInput> associateInput) {
        return associateInput
                .map(AssociateMapper.INSTANCE::map)
                .flatMap(a -> this.createAssociateUseCase.execute(Mono.just(a)))
                .map(AssociateMapper.INSTANCE::map)
                .map(a -> ResponseEntity.status(CREATED).body(a));
    }

    @Override
    @ResponseStatus(NO_CONTENT)
    public Mono<ResponseEntity<Void>> deleteAssociate(final String associateId) {
        return this.deleteAssociateByIdUseCase.execute(associateId)
                .map(a -> ResponseEntity.status(NO_CONTENT).build());
    }

    @Override
    public Mono<ResponseEntity<AssociateResult>> getAssociate(final String associateId) {
        return this.getAssociateByIdUseCase.execute(associateId)
                .map(AssociateMapper.INSTANCE::map)
                .map(ResponseEntity::ok);
    }

    @Override
    public Flux<AssociateResult> getAllAssociates() {
        return this.getAllAssociatesUseCase.execute()
                .map(AssociateMapper.INSTANCE::map);
    }

    @Override
    public Mono<ResponseEntity<AssociateResult>> updateAssociate(final Mono<AssociateInput> associateInput) {
        return associateInput
                .map(AssociateMapper.INSTANCE::map)
                .flatMap(a -> this.createAssociateUseCase.execute(Mono.just(a)))
                .map(AssociateMapper.INSTANCE::map)
                .map(ResponseEntity::ok);
    }

}