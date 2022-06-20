package com.sicredi.votacao.adapter.datasources.services.mapper;

import com.sicredi.votacao.adapter.datasources.services.model.SchedulleModel;
import com.sicredi.votacao.internal.entities.Schedulle;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-03T15:12:54-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Private Build)"
)
public class SchedulleMapperImpl implements SchedulleMapper {

    @Override
    public SchedulleModel map(Schedulle source) {
        if ( source == null ) {
            return null;
        }

        SchedulleModel schedulleModel = new SchedulleModel();

        schedulleModel.setId( source.getId() );
        schedulleModel.setName( source.getName() );

        return schedulleModel;
    }

    @Override
    public Schedulle map(SchedulleModel source) {
        if ( source == null ) {
            return null;
        }

        Schedulle schedulle = new Schedulle();

        schedulle.setId( source.getId() );
        schedulle.setName( source.getName() );

        return schedulle;
    }
}
