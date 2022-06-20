package com.sicredi.votacao.adapter.datasources.services.mapper;

import com.sicredi.votacao.adapter.datasources.services.model.AssociateModel;
import com.sicredi.votacao.internal.entities.Associate;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-03T15:12:54-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Private Build)"
)
public class AssociateMapperImpl implements AssociateMapper {

    @Override
    public AssociateModel map(Associate source) {
        if ( source == null ) {
            return null;
        }

        AssociateModel associateModel = new AssociateModel();

        associateModel.setId( source.getId() );
        associateModel.setCpf( source.getCpf() );

        return associateModel;
    }

    @Override
    public Associate map(AssociateModel source) {
        if ( source == null ) {
            return null;
        }

        Associate associate = new Associate();

        associate.setId( source.getId() );
        associate.setCpf( source.getCpf() );

        return associate;
    }
}
