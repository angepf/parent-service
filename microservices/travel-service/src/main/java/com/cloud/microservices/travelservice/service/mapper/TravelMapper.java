package com.cloud.microservices.travelservice.service.mapper;

import com.cloud.dependencies.dtoservice.dto.travelservice.TravelDto;
import com.cloud.microservices.travelservice.datajpa.entity.Travel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TravelMapper {

    @Mapping(target = "routeId", source = "routeId", ignore = true)
    @Mapping(target = "transportId", source = "transportId", ignore = true)
    @Mapping(target = "userId", source = "userId", ignore = true)
    TravelDto toTravelDto(Travel travel);

    @Mapping(target = "routeId", source = "routeId", ignore = true)
    @Mapping(target = "transportId", source = "transportId", ignore = true)
    @Mapping(target = "userId", source = "userId", ignore = true)
    Travel toTravel(TravelDto travelDto);

}