package com.cloud.microservices.travelservice.service.mapper;

import com.cloud.dependencies.dtoservice.dto.travelservice.RouteDto;
import com.cloud.microservices.travelservice.datajpa.entity.Route;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RouteMapper {

    @Mapping(target = "userId", source = "userId", ignore = true)
    RouteDto toRouteDto(Route route);

    @Mapping(target = "userId", source = "userId", ignore = true)
    Route toRoute(RouteDto routeDto);

}