package com.sicredi.votacao.adapter.transportlayers.mapper;

import com.sicredi.votacao.adapter.transportlayers.openapi.model.AssociateInput;
import com.sicredi.votacao.adapter.transportlayers.openapi.model.AssociateResult;
import com.sicredi.votacao.internal.entities.Associate;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-03T15:12:54-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Private Build)"
)
public class AssociateMapperImpl implements AssociateMapper {

    @Override
    public AssociateResult map(Associate source) {
        if ( source == null ) {
            return null;
        }

        AssociateResult associateResult = new AssociateResult();

        associateResult.setId( source.getId() );
        associateResult.setCpf( source.getCpf() );

        return associateResult;
    }

    @Override
    public Associate map(AssociateInput source) {
        if ( source == null ) {
            return null;
        }

        Associate associate = new Associate();

        associate.setCpf( source.getCpf() );

        return associate;
    }
}
