package com.cloud.microservices.travelservice.service.impl;

import com.cloud.dependencies.dtoservice.dto.actorservice.UserDto;
import com.cloud.dependencies.dtoservice.dto.travelservice.RouteDto;
import com.cloud.dependencies.dtoservice.dto.travelservice.TransportDto;
import com.cloud.dependencies.dtoservice.dto.travelservice.TravelDto;
import com.cloud.dependencies.dtoservice.exception.ResourceNotFoundException;
import com.cloud.microservices.travelservice.datajpa.entity.Travel;
import com.cloud.microservices.travelservice.external.service.ActorService;
import com.cloud.microservices.travelservice.datajpa.repository.TravelRepository;
import com.cloud.microservices.travelservice.service.RouteService;
import com.cloud.microservices.travelservice.service.TransportService;
import com.cloud.microservices.travelservice.service.TravelService;
import com.cloud.microservices.travelservice.service.mapper.RouteMapper;
import com.cloud.microservices.travelservice.service.mapper.TransportMapper;
import com.cloud.microservices.travelservice.service.mapper.TravelMapper;
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
public class TravelServiceImpl implements TravelService {

    TravelRepository travelRepository;

    TravelMapper travelMapper;

    ActorService actorService;

    TransportService transportService;

    TransportMapper transportMapper;
    RouteService routeService;

    RouteMapper routeMapper;

    @Override
    public TravelDto getTravelById(Integer travelId) {
        log.info( "Start service :: getTravelById: [{}]", travelId );

        Travel travel = travelRepository.findById( travelId )
                .orElseThrow( () -> new ResourceNotFoundException( "Travel", "id", travelId.toString() ) );

        TravelDto travelDto = travelMapper.toTravelDto( travel );

        TransportDto transportDto = transportService.getTransportById( travel.getTransportId().getLicencePlate() );
        RouteDto routeDto = routeService.getRouteById( travel.getRouteId().getId() );
        UserDto userDto = actorService.getUserById( travel.getUserId() );

        travelDto.setUserId( userDto );
        travelDto.setRouteId( routeDto );
        travelDto.setTransportId( transportDto );
        return travelDto;
    }

    @Override
    public TravelDto createTravel(TravelDto travelDto) {
        log.info( "Service :: createTravel: [{}]", travelDto );

        TransportDto transportDto = transportService.getTransportById( travelDto.getTransportId().getLicencePlate() );
        RouteDto routeDto = routeService.getRouteById( travelDto.getRouteId().getId() );
        UserDto userDto = actorService.getUserById( travelDto.getUserId().getId() );

        Travel travel = travelMapper.toTravel( travelDto );

        ValidateFields.validateField( "Route", routeDto.getId().toString(), transportDto.getUserId().getId() );
        travel.setRouteId( routeMapper.toRoute( routeDto ) );

        ValidateFields.validateField( "Transport", transportDto.getLicencePlate(), transportDto.getUserId().getId() );
        travel.setTransportId( transportMapper.toTransport( transportDto ) );

        ValidateFields.validateField( "User", userDto.getId(), transportDto.getUserId().getId() );
        travel.setUserId( userDto.getId() );

        travel.setStatus( true );

        travelDto = travelMapper.toTravelDto( travelRepository.save( travel ) );
        travelDto.setRouteId( routeDto );
        travelDto.setTransportId( transportDto );
        travelDto.setUserId( userDto );

        return travelDto;
    }
}
