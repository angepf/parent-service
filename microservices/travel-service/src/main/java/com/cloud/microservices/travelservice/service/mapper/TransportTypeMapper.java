package com.cloud.microservices.travelservice.service.mapper;

import com.cloud.dependencies.dtoservice.dto.travelservice.TransportTypeDto;
import com.cloud.microservices.travelservice.datajpa.entity.TransportType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransportTypeMapper {

    TransportTypeDto toTransportTypeDto(TransportType transportType);

    TransportType toTransportType(TransportTypeDto transportTypeDto);

}