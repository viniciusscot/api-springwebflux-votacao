package com.sicredi.votacao.adapter.transportlayers.mapper;

import com.sicredi.votacao.adapter.transportlayers.restapi.dto.VoteInput;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.VoteResult;
import com.sicredi.votacao.internal.entities.Vote;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-22T22:48:27-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Private Build)"
)
public class VoteMapperImpl implements VoteMapper {

    @Override
    public VoteResult map(Vote source) {
        if ( source == null ) {
            return null;
        }

        VoteResult voteResult = new VoteResult();

        voteResult.setHorary( source.getHorary() );
        voteResult.setDecision( source.getDecision() );
        voteResult.setAssociateId( source.getAssociateId() );
        voteResult.setSchedulleId( source.getSchedulleId() );

        return voteResult;
    }

    @Override
    public Vote map(VoteInput source) {
        if ( source == null ) {
            return null;
        }

        Vote vote = new Vote();

        vote.setDecision( source.getDecision() );
        vote.setAssociateId( source.getAssociateId() );
        vote.setSchedulleId( source.getSchedulleId() );

        return vote;
    }
}
