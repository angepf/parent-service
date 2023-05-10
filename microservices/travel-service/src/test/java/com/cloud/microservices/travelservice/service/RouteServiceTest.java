package com.cloud.microservices.travelservice.service;

import com.cloud.dependencies.dtoservice.exception.ResourceNotFoundException;
import com.cloud.dependencies.dtoservice.mockData.RouteDtoMock;
import com.cloud.dependencies.dtoservice.mockData.UserDtoMock;
import com.cloud.microservices.travelservice.datajpa.repository.RouteRepository;
import com.cloud.microservices.travelservice.external.service.ActorService;
import com.cloud.microservices.travelservice.mockdata.RouteMock;
import com.cloud.microservices.travelservice.service.impl.RouteServiceImpl;
import com.cloud.microservices.travelservice.service.mapper.RouteMapper;
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
public class RouteServiceTest {

    @Mock
    RouteRepository routeRepository;

    @Mock
    RouteMapper routeMapper;

    @Mock
    ActorService actorService;

    @InjectMocks
    RouteServiceImpl routeService;

    @DisplayName("GetRoute - OK")
    @Test
    void whenGetRouteByIdThenOkResponse() {
        Mockito.when( routeRepository.findById( Mockito.any() ) )
                .thenReturn( Optional.of( RouteMock.getRouteMock() ) );

        Mockito.when( routeMapper.toRouteDto( Mockito.any() ) )
                .thenReturn( RouteDtoMock.getRouteDtoMock() );

        Mockito.when( actorService.getUserById( Mockito.any() ) )
                .thenReturn( UserDtoMock.getUserDtoMock() );

        Assertions.assertEquals( RouteMock.getRouteMock().getId(), routeService.getRouteById( 1 ).getId() );

    }

    @DisplayName("GetRoute - Exception")
    @Test
    void whenGetRouteByIdThenExceptionResponse() {
        Mockito.when( routeRepository.findById( Mockito.any() ) )
                .thenThrow( new ResourceNotFoundException( "Route", "id", "3" ) );

        Mockito.when( routeMapper.toRouteDto( Mockito.any() ) )
                .thenReturn( RouteDtoMock.getRouteDtoMock() );

        Mockito.when( actorService.getUserById( Mockito.any() ) )
                .thenReturn( UserDtoMock.getUserDtoMock() );

        Exception exception = Assertions.assertThrows(
                ResourceNotFoundException.class, () -> routeService.getRouteById( 3 ) );

        Assertions.assertEquals( ResourceNotFoundException.class, exception.getClass() );
    }

    @DisplayName("CreateRoute - OK")
    @Test
    void whenCreateRouteByIdThenOkResponse() {
        Mockito.when( routeRepository.save( Mockito.any() ) )
                .thenReturn( RouteMock.getRouteMock() );

        Mockito.when( routeMapper.toRouteDto( Mockito.any() ) )
                .thenReturn( RouteDtoMock.getRouteDtoMock() );

        Mockito.when( routeMapper.toRoute( Mockito.any() ) )
                .thenReturn( RouteMock.getRouteMock() );

        Mockito.when( actorService.getUserById( Mockito.any() ) )
                .thenReturn( UserDtoMock.getUserDtoMock() );

        Assertions.assertEquals( RouteDtoMock.getRouteDtoMock().getId(), routeService.createRoute( RouteDtoMock.getRouteDtoMock() ).getId() );
    }

    @DisplayName("CreateRoute - Exception")
    @Test
    void whenCreateRouteByIdThenException1Response() {
        Mockito.when( routeRepository.save( Mockito.any() ) )
                .thenReturn( RouteMock.getRouteMock() );

        Mockito.when( routeMapper.toRouteDto( Mockito.any() ) )
                .thenReturn( RouteDtoMock.getRouteDtoMock() );

        Mockito.when( routeMapper.toRoute( Mockito.any() ) )
                .thenReturn( RouteMock.getRouteMock() );

        Mockito.when( actorService.getUserById( Mockito.any() ) )
                .thenThrow( new ResourceNotFoundException( "User", "id", "id" ) );

        Exception exception = Assertions.assertThrows(
                ResourceNotFoundException.class, () -> routeService.createRoute( RouteDtoMock.getRouteDtoMock() ) );

        Assertions.assertEquals( ResourceNotFoundException.class, exception.getClass() );
    }
}
