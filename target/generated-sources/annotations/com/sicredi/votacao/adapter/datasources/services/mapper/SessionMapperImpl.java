package com.sicredi.votacao.adapter.datasources.services.mapper;

import com.sicredi.votacao.adapter.datasources.services.event.SessionEvent;
import com.sicredi.votacao.adapter.datasources.services.model.SessionModel;
import com.sicredi.votacao.internal.entities.Session;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-03T15:12:54-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Private Build)"
)
public class SessionMapperImpl implements SessionMapper {

    @Override
    public SessionModel map(Session source) {
        if ( source == null ) {
            return null;
        }

        SessionModel sessionModel = new SessionModel();

        sessionModel.setId( source.getId() );
        sessionModel.setStartDate( source.getStartDate() );
        sessionModel.setEndDate( source.getEndDate() );
        sessionModel.setFinished( source.getFinished() );
        sessionModel.setDurationInMinutes( source.getDurationInMinutes() );
        sessionModel.setSchedulleId( source.getSchedulleId() );

        return sessionModel;
    }

    @Override
    public Session map(SessionModel source) {
        if ( source == null ) {
            return null;
        }

        Session session = new Session();

        session.setId( source.getId() );
        session.setStartDate( source.getStartDate() );
        session.setEndDate( source.getEndDate() );
        session.setFinished( source.getFinished() );
        session.setDurationInMinutes( source.getDurationInMinutes() );
        session.setSchedulleId( source.getSchedulleId() );

        return session;
    }

    @Override
    public SessionEvent mapEvent(Session source) {
        if ( source == null ) {
            return null;
        }

        SessionEvent sessionEvent = new SessionEvent();

        sessionEvent.setId( source.getId() );
        sessionEvent.setStartDate( source.getStartDate() );
        sessionEvent.setEndDate( source.getEndDate() );
        sessionEvent.setFinished( source.getFinished() );
        sessionEvent.setDurationInMinutes( source.getDurationInMinutes() );
        sessionEvent.setSchedulleId( source.getSchedulleId() );

        return sessionEvent;
    }
}
