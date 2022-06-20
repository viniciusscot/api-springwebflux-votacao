package com.sicredi.votacao.adapter.transportlayers.mapper;

import com.sicredi.votacao.adapter.transportlayers.openapi.model.ResultOfSchedulleResult;
import com.sicredi.votacao.adapter.transportlayers.openapi.model.SchedulleInput;
import com.sicredi.votacao.adapter.transportlayers.openapi.model.SchedulleResult;
import com.sicredi.votacao.adapter.transportlayers.openapi.model.SessionResult;
import com.sicredi.votacao.internal.entities.ResultOfSchedulle;
import com.sicredi.votacao.internal.entities.Schedulle;
import com.sicredi.votacao.internal.entities.Session;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-03T15:12:54-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Private Build)"
)
public class SchedulleMapperImpl implements SchedulleMapper {

    @Override
    public SchedulleResult map(Schedulle source) {
        if ( source == null ) {
            return null;
        }

        SchedulleResult schedulleResult = new SchedulleResult();

        schedulleResult.setId( source.getId() );
        schedulleResult.setName( source.getName() );

        return schedulleResult;
    }

    @Override
    public Schedulle map(SchedulleInput source) {
        if ( source == null ) {
            return null;
        }

        Schedulle schedulle = new Schedulle();

        schedulle.setName( source.getName() );

        return schedulle;
    }

    @Override
    public ResultOfSchedulleResult map(ResultOfSchedulle source) {
        if ( source == null ) {
            return null;
        }

        ResultOfSchedulleResult resultOfSchedulleResult = new ResultOfSchedulleResult();

        resultOfSchedulleResult.setSession( sessionToSessionResult( source.getSession() ) );
        resultOfSchedulleResult.setVotes( source.getVotes() );
        resultOfSchedulleResult.setVotesYes( source.getVotesYes() );
        resultOfSchedulleResult.setVotesNo( source.getVotesNo() );
        resultOfSchedulleResult.setResult( source.getResult() );

        return resultOfSchedulleResult;
    }

    protected SessionResult sessionToSessionResult(Session session) {
        if ( session == null ) {
            return null;
        }

        SessionResult sessionResult = new SessionResult();

        sessionResult.setId( session.getId() );
        sessionResult.setStartDate( session.getStartDate() );
        sessionResult.setEndDate( session.getEndDate() );
        if ( session.getDurationInMinutes() != null ) {
            sessionResult.setDurationInMinutes( session.getDurationInMinutes().toString() );
        }

        return sessionResult;
    }
}
