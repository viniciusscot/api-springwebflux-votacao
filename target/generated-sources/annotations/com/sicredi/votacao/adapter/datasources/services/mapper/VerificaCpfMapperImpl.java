package com.sicredi.votacao.adapter.datasources.services.mapper;

import com.sicredi.votacao.adapter.datasources.services.data.response.VerifyCpfResponse;
import com.sicredi.votacao.adapter.datasources.services.data.response.VerifyCpfResponse.StatusEnum;
import com.sicredi.votacao.internal.entities.VerifyCpf;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-22T22:48:26-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (Private Build)"
)
public class VerificaCpfMapperImpl implements VerificaCpfMapper {

    @Override
    public VerifyCpf map(VerifyCpfResponse source) {
        if ( source == null ) {
            return null;
        }

        VerifyCpf verifyCpf = new VerifyCpf();

        verifyCpf.setStatus( statusEnumToStatusEnum( source.getStatus() ) );

        return verifyCpf;
    }

    protected com.sicredi.votacao.internal.entities.VerifyCpf.StatusEnum statusEnumToStatusEnum(StatusEnum statusEnum) {
        if ( statusEnum == null ) {
            return null;
        }

        com.sicredi.votacao.internal.entities.VerifyCpf.StatusEnum statusEnum1;

        switch ( statusEnum ) {
            case ABLE_TO_VOTE: statusEnum1 = com.sicredi.votacao.internal.entities.VerifyCpf.StatusEnum.ABLE_TO_VOTE;
            break;
            case UNABLE_TO_VOTE: statusEnum1 = com.sicredi.votacao.internal.entities.VerifyCpf.StatusEnum.UNABLE_TO_VOTE;
            break;
            default: throw new IllegalArgumentException( "Unexpected enum constant: " + statusEnum );
        }

        return statusEnum1;
    }
}
