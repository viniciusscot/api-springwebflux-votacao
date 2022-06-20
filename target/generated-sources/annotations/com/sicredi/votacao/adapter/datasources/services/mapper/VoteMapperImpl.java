package com.sicredi.votacao.adapter.datasources.services.mapper;

import com.sicredi.votacao.adapter.datasources.services.model.VoteModel;
import com.sicredi.votacao.internal.entities.Vote;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-03T15:12:54-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Private Build)"
)
public class VoteMapperImpl implements VoteMapper {

    @Override
    public VoteModel map(Vote source) {
        if ( source == null ) {
            return null;
        }

        VoteModel voteModel = new VoteModel();

        voteModel.setId( source.getId() );
        voteModel.setHorary( source.getHorary() );
        voteModel.setDecision( source.getDecision() );
        voteModel.setAssociateId( source.getAssociateId() );
        voteModel.setSchedulleId( source.getSchedulleId() );
        voteModel.setSessionId( source.getSessionId() );

        return voteModel;
    }

    @Override
    public Vote map(VoteModel source) {
        if ( source == null ) {
            return null;
        }

        Vote vote = new Vote();

        vote.setId( source.getId() );
        vote.setHorary( source.getHorary() );
        vote.setDecision( source.getDecision() );
        vote.setAssociateId( source.getAssociateId() );
        vote.setSchedulleId( source.getSchedulleId() );
        vote.setSessionId( source.getSessionId() );

        return vote;
    }
}
