package com.cloud.microservices.travelservice.service.impl;

import com.cloud.dependencies.dtoservice.dto.actorservice.UserDto;
import com.cloud.dependencies.dtoservice.exception.ResourceNotFoundException;
import com.cloud.dependencies.dtoservice.dto.travelservice.RouteDto;
import com.cloud.microservices.travelservice.datajpa.entity.Route;
import com.cloud.microservices.travelservice.external.service.ActorService;
import com.cloud.microservices.travelservice.datajpa.repository.RouteRepository;
import com.cloud.microservices.travelservice.service.RouteService;
import com.cloud.microservices.travelservice.service.mapper.RouteMapper;
import com.cloud.microservices.travelservice.util.ValidateFields;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RouteServiceImpl implements RouteService {

    RouteRepository routeRepository;

    RouteMapper routeMapper;

    ActorService actorService;

    @Override
    public RouteDto getRouteById(Integer routeId) {
        log.info( "Start service :: getRouteById: [{}]", routeId );

        Route route = routeRepository.findById( routeId )
                .orElseThrow( () -> new ResourceNotFoundException( "Route", "id", routeId.toString() ) );

        UserDto userDto = actorService.getUserById( route.getUserId() );

        RouteDto routeDto = routeMapper.toRouteDto( route );
        routeDto.setUserId( userDto );

        return routeDto;
    }

    @Override
    public RouteDto createRoute(RouteDto routeDto) {
        log.info( "Service :: createRoute: [{}]", routeDto );

        UserDto userDto = actorService.getUserById( routeDto.getUserId().getId() );

        Route route = routeMapper.toRoute( routeDto );

        ValidateFields.validateField( "User", userDto.getId(), routeDto.getUserId().getId() );
        route.setUserId( userDto.getId() );

        routeDto = routeMapper.toRouteDto( routeRepository.save( route ) );
        routeDto.setUserId( userDto );

        return routeDto;

    }
}
