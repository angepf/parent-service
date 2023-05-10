package com.cloud.microservices.travelservice.service;

import com.cloud.dependencies.dtoservice.exception.ResourceNotFoundException;
import com.cloud.dependencies.dtoservice.mockData.RouteDtoMock;
import com.cloud.dependencies.dtoservice.mockData.TransportDtoMock;
import com.cloud.dependencies.dtoservice.mockData.TravelDtoMock;
import com.cloud.dependencies.dtoservice.mockData.UserDtoMock;
import com.cloud.microservices.travelservice.datajpa.repository.TravelRepository;
import com.cloud.microservices.travelservice.external.service.ActorService;
import com.cloud.microservices.travelservice.mockdata.RouteMock;
import com.cloud.microservices.travelservice.mockdata.TransportMock;
import com.cloud.microservices.travelservice.mockdata.TravelMock;
import com.cloud.microservices.travelservice.service.impl.TravelServiceImpl;
import com.cloud.microservices.travelservice.service.mapper.RouteMapper;
import com.cloud.microservices.travelservice.service.mapper.TransportMapper;
import com.cloud.microservices.travelservice.service.mapper.TravelMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class TravelServiceTest {

    @Mock
    TravelRepository travelRepository;

    @Mock
    TravelMapper travelMapper;

    @Mock
    TransportService transportService;

    @Mock
    RouteService routeService;

    @Mock
    RouteMapper routeMapper;

    @Mock
    TransportMapper transportMapper;

    @Mock
    ActorService actorService;

    @InjectMocks
    TravelServiceImpl travelService;

    @DisplayName("GetTravel - OK")
    @Test
    void whenGetTravelByIdThenOkResponse() {
        Mockito.when( travelRepository.findById( Mockito.any() ) )
                .thenReturn( Optional.of( TravelMock.getTravelMock() ) );

        Mockito.when( travelMapper.toTravelDto( Mockito.any() ) )
                .thenReturn( TravelDtoMock.getTravelDtoMock() );

        Mockito.when( actorService.getUserById( Mockito.any() ) )
                .thenReturn( UserDtoMock.getUserDtoMock() );

        Assertions.assertEquals( TravelMock.getTravelMock().getId(), travelService.getTravelById( 1 ).getId() );

    }

    @DisplayName("GetTravel - Exception")
    @Test
    void whenGetTravelByIdThenExceptionResponse() {
        Mockito.when( travelRepository.findById( Mockito.any() ) )
                .thenThrow( new ResourceNotFoundException( "Travel", "id", "3" ) );

        Mockito.when( travelMapper.toTravelDto( Mockito.any() ) )
                .thenReturn( TravelDtoMock.getTravelDtoMock() );

        Mockito.when( actorService.getUserById( Mockito.any() ) )
                .thenReturn( UserDtoMock.getUserDtoMock() );

        Exception exception = Assertions.assertThrows(
                ResourceNotFoundException.class, () -> travelService.getTravelById( 3 ) );

        Assertions.assertEquals( ResourceNotFoundException.class, exception.getClass() );
    }

    @DisplayName("CreateTravel - OK")
    @Test
    void whenCreateTravelByIdThenOkResponse() {
        Mockito.when( travelRepository.save( Mockito.any() ) )
                .thenReturn( TravelMock.getTravelMock() );

        Mockito.when( travelMapper.toTravelDto( Mockito.any() ) )
                .thenReturn( TravelDtoMock.getTravelDtoMock() );

        Mockito.when( travelMapper.toTravel( Mockito.any() ) )
                .thenReturn( TravelMock.getTravelMock() );

        Mockito.when( transportService.getTransportById( Mockito.any() ) )
                .thenReturn( TransportDtoMock.getTransportDtoMock() );

        Mockito.when( routeService.getRouteById( Mockito.any() ) )
                .thenReturn( RouteDtoMock.getRouteDtoMock() );

        Mockito.when( routeMapper.toRoute( Mockito.any() ) )
                .thenReturn( RouteMock.getRouteMock() );

        Mockito.when( transportMapper.toTransport( Mockito.any() ) )
                .thenReturn( TransportMock.getTransportMock() );

        Mockito.when( actorService.getUserById( Mockito.any() ) )
                .thenReturn( UserDtoMock.getUserDtoMock() );

        Assertions.assertEquals( TravelDtoMock.getTravelDtoMock().getId(), travelService.createTravel( TravelDtoMock.getTravelDtoMock() ).getId() );
    }

    @DisplayName("CreateTravel - Exception")
    @Test
    void whenCreateTravelByIdThenException1Response() {
        Mockito.when( travelRepository.save( Mockito.any() ) )
                .thenReturn( TravelMock.getTravelMock() );

        Mockito.when( travelMapper.toTravelDto( Mockito.any() ) )
                .thenReturn( TravelDtoMock.getTravelDtoMock() );

        Mockito.when( travelMapper.toTravel( Mockito.any() ) )
                .thenReturn( TravelMock.getTravelMock() );

        Mockito.when( transportService.getTransportById( Mockito.any() ) )
                .thenReturn( TransportDtoMock.getTransportDtoMock() );

        Mockito.when( routeService.getRouteById( Mockito.any() ) )
                .thenReturn( RouteDtoMock.getRouteDtoMock() );

        Mockito.when( actorService.getUserById( Mockito.any() ) )
                .thenThrow( new ResourceNotFoundException( "User", "id", "id" ) );

        Exception exception = Assertions.assertThrows(
                ResourceNotFoundException.class, () -> travelService.createTravel( TravelDtoMock.getTravelDtoMock() ) );

        Assertions.assertEquals( ResourceNotFoundException.class, exception.getClass() );
    }
}
