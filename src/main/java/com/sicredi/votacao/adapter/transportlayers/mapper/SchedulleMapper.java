package com.sicredi.votacao.adapter.transportlayers.mapper;

import com.sicredi.votacao.adapter.transportlayers.restapi.dto.ResultOfSchedulleResult;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.SchedulleInput;
import com.sicredi.votacao.adapter.transportlayers.restapi.dto.SchedulleResult;
import com.sicredi.votacao.internal.entities.ResultOfSchedulle;
import com.sicredi.votacao.internal.entities.Schedulle;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SchedulleMapper {

    SchedulleMapper INSTANCE = Mappers.getMapper(SchedulleMapper.class);

    SchedulleResult map(Schedulle source);

    Schedulle map(SchedulleInput source);

    ResultOfSchedulleResult map(ResultOfSchedulle source);
}

