package com.cloud.microservices.travelservice.service;

import com.cloud.dependencies.dtoservice.exception.ResourceNotFoundException;
import com.cloud.dependencies.dtoservice.mockData.*;
import com.cloud.microservices.travelservice.datajpa.repository.TransportRepository;
import com.cloud.microservices.travelservice.external.service.ActorService;
import com.cloud.microservices.travelservice.mockdata.TransportMock;
import com.cloud.microservices.travelservice.service.impl.TransportServiceImpl;
import com.cloud.microservices.travelservice.service.mapper.TransportMapper;
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
public class TransportServiceTest {

    @Mock
    TransportRepository transportRepository;

    @Mock
    TransportMapper transportMapper;

    @Mock
    ActorService actorService;

    @Mock
    TransportTypeService transportTypeService;

    @InjectMocks
    TransportServiceImpl transportService;

    @DisplayName("GetTransport - OK")
    @Test
    void whenGetTransportByIdThenOkResponse() {
        Mockito.when( transportRepository.findById( Mockito.any() ) )
                .thenReturn( Optional.of( TransportMock.getTransportMock() ) );

        Mockito.when( transportMapper.toTransportDto( Mockito.any() ) )
                .thenReturn( TransportDtoMock.getTransportDtoMock() );

        Mockito.when( actorService.getUserById( Mockito.any() ) )
                .thenReturn( UserDtoMock.getUserDtoMock() );

        Assertions.assertEquals( TransportMock.getTransportMock().getLicencePlate(),
                transportService.getTransportById( "ABG4543" ).getLicencePlate() );

    }

    @DisplayName("GetTransport - Exception")
    @Test
    void whenGetTransportByIdThenExceptionResponse() {
        Mockito.when( transportRepository.findById( Mockito.any() ) )
                .thenThrow( new ResourceNotFoundException( "Transport", "id", "3" ) );

        Mockito.when( transportMapper.toTransportDto( Mockito.any() ) )
                .thenReturn( TransportDtoMock.getTransportDtoMock() );

        Mockito.when( actorService.getUserById( Mockito.any() ) )
                .thenReturn( UserDtoMock.getUserDtoMock() );

        Exception exception = Assertions.assertThrows(
                ResourceNotFoundException.class, () -> transportService.getTransportById( "ABF4354" ) );

        Assertions.assertEquals( ResourceNotFoundException.class, exception.getClass() );
    }

    @DisplayName("CreateTransport - OK")
    @Test
    void whenCreateTransportByIdThenOkResponse() {
        Mockito.when( transportRepository.save( Mockito.any() ) )
                .thenReturn( TransportMock.getTransportMock() );

        Mockito.when( transportMapper.toTransportDto( Mockito.any() ) )
                .thenReturn( TransportDtoMock.getTransportDtoMock() );

        Mockito.when( transportMapper.toTransport( Mockito.any() ) )
                .thenReturn( TransportMock.getTransportMock() );

        Mockito.when( transportTypeService.getTransportTypeById( Mockito.any() ) )
                .thenReturn( TransportTypeDtoMock.getTransportTypeDtoMock() );

        Mockito.when( actorService.getUserById( Mockito.any() ) )
                .thenReturn( UserDtoMock.getUserDtoMock() );

        Mockito.when( actorService.getDriverById( Mockito.any() ) )
                .thenReturn( DriverDtoMock.getDriverDtoMock() );

        Mockito.when( actorService.getOwnerById( Mockito.any() ) )
                .thenReturn( OwnerDtoMock.getOwnerDtoMock() );

        Mockito.when( actorService.getUserById( Mockito.any() ) )
                .thenReturn( UserDtoMock.getUserDtoMock() );

        Assertions.assertEquals( TransportDtoMock.getTransportDtoMock().getLicencePlate(),
                transportService.createTransport( TransportDtoMock.getTransportDtoMock() ).getLicencePlate() );
    }

    @DisplayName("CreateTransport - Exception")
    @Test
    void whenCreateTransportByIdThenException1Response() {
        Mockito.when( transportRepository.save( Mockito.any() ) )
                .thenReturn( TransportMock.getTransportMock() );

        Mockito.when( transportMapper.toTransportDto( Mockito.any() ) )
                .thenReturn( TransportDtoMock.getTransportDtoMock() );

        Mockito.when( transportMapper.toTransport( Mockito.any() ) )
                .thenReturn( TransportMock.getTransportMock() );

        Mockito.when( transportTypeService.getTransportTypeById( Mockito.any() ) )
                .thenReturn( TransportTypeDtoMock.getTransportTypeDtoMock() );

        Mockito.when( actorService.getUserById( Mockito.any() ) )
                .thenThrow( new ResourceNotFoundException( "User", "id", "id" ) );

        Exception exception = Assertions.assertThrows(
                ResourceNotFoundException.class, () -> transportService.createTransport( TransportDtoMock.getTransportDtoMock() ) );

        Assertions.assertEquals( ResourceNotFoundException.class, exception.getClass() );
    }
}
