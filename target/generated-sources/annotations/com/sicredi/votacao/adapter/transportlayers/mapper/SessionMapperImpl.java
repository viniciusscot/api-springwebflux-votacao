package com.sicredi.votacao.adapter.transportlayers.mapper;

import com.sicredi.votacao.adapter.transportlayers.openapi.model.SessionInput;
import com.sicredi.votacao.adapter.transportlayers.openapi.model.SessionResult;
import com.sicredi.votacao.internal.entities.Session;
import java.math.BigInteger;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-03T15:12:54-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Private Build)"
)
public class SessionMapperImpl implements SessionMapper {

    @Override
    public SessionResult map(Session source) {
        if ( source == null ) {
            return null;
        }

        SessionResult sessionResult = new SessionResult();

        sessionResult.setId( source.getId() );
        sessionResult.setStartDate( source.getStartDate() );
        sessionResult.setEndDate( source.getEndDate() );
        if ( source.getDurationInMinutes() != null ) {
            sessionResult.setDurationInMinutes( source.getDurationInMinutes().toString() );
        }

        return sessionResult;
    }

    @Override
    public Session map(SessionInput source) {
        if ( source == null ) {
            return null;
        }

        Session session = new Session();

        if ( source.getDurationInMinutes() != null ) {
            session.setDurationInMinutes( BigInteger.valueOf( source.getDurationInMinutes() ) );
        }
        session.setSchedulleId( source.getSchedulleId() );

        return session;
    }
}
