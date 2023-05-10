package com.cloud.microservices.travelservice.service;

import com.cloud.dependencies.dtoservice.dto.travelservice.RouteDto;

public interface RouteService {

    RouteDto getRouteById(Integer routeId);

    RouteDto createRoute(RouteDto routeDto);

}
