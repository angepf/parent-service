package com.cloud.microservices.travelservice.service.mapper;

import com.cloud.dependencies.dtoservice.dto.travelservice.TransportDto;
import com.cloud.microservices.travelservice.datajpa.entity.Transport;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransportMapper {

    @Mapping(target = "ownerId", source = "ownerId", ignore = true)
    @Mapping(target = "driverId", source = "driverId", ignore = true)
    @Mapping(target = "userId", source = "userId", ignore = true)
    TransportDto toTransportDto(Transport transport);

    @Mapping(target = "ownerId", source = "ownerId", ignore = true)
    @Mapping(target = "driverId", source = "driverId", ignore = true)
    @Mapping(target = "userId", source = "userId", ignore = true)
    Transport toTransport(TransportDto transportDto);

}