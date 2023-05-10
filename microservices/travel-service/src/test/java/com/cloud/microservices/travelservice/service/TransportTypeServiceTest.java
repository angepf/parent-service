package com.cloud.microservices.travelservice.service;

import com.cloud.dependencies.dtoservice.exception.ResourceNotFoundException;
import com.cloud.dependencies.dtoservice.mockData.TransportTypeDtoMock;
import com.cloud.microservices.travelservice.datajpa.repository.TransportTypeRepository;
import com.cloud.microservices.travelservice.mockdata.TransportTypeMock;
import com.cloud.microservices.travelservice.service.impl.TransportTypeServiceImpl;
import com.cloud.microservices.travelservice.service.mapper.TransportTypeMapper;
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
public class TransportTypeServiceTest {

    @Mock
    TransportTypeRepository transportTypeRepository;

    @Mock
    TransportTypeMapper transportTypeMapper;

    @InjectMocks
    TransportTypeServiceImpl transportTypeService;

    @DisplayName("GetTransportType - OK")
    @Test
    void whenGetTransportTypeByIdThenOkResponse() {
        Mockito.when( transportTypeRepository.findById( Mockito.any() ) )
                .thenReturn( Optional.of( TransportTypeMock.getTransportTypeMock() ) );

        Mockito.when( transportTypeMapper.toTransportTypeDto( Mockito.any() ) )
                .thenReturn( TransportTypeDtoMock.getTransportTypeDtoMock() );

        Assertions.assertEquals( TransportTypeMock.getTransportTypeMock().getId(), transportTypeService.getTransportTypeById( 1 ).getId() );

    }

    @DisplayName("GetTransportType - Exception")
    @Test
    void whenGetTransportTypeByIdThenExceptionResponse() {
        Mockito.when( transportTypeRepository.findById( Mockito.any() ) )
                .thenThrow( new ResourceNotFoundException( "TransportType", "id", "3" ) );

        Exception exception = Assertions.assertThrows(
                ResourceNotFoundException.class, () -> transportTypeService.getTransportTypeById( 3 ) );

        Assertions.assertEquals( ResourceNotFoundException.class, exception.getClass() );
    }

    @DisplayName("CreateTransportType - OK")
    @Test
    void whenCreateTransportTypeByIdThenOkResponse() {
        Mockito.when( transportTypeRepository.save( Mockito.any() ) )
                .thenReturn( TransportTypeMock.getTransportTypeMock() );

        Mockito.when( transportTypeMapper.toTransportTypeDto( Mockito.any() ) )
                .thenReturn( TransportTypeDtoMock.getTransportTypeDtoMock() );

        Mockito.when( transportTypeMapper.toTransportType( Mockito.any() ) )
                .thenReturn( TransportTypeMock.getTransportTypeMock() );

        Assertions.assertEquals( TransportTypeDtoMock.getTransportTypeDtoMock().getId(), transportTypeService.createTransportType( TransportTypeDtoMock.getTransportTypeDtoMock() ).getId() );
    }

    @DisplayName("CreateTransportType - Exception")
    @Test
    void whenCreateTransportTypeByIdThenException1Response() {
        Mockito.when( transportTypeRepository.save( Mockito.any() ) )
                .thenThrow( new ResourceNotFoundException( "TransporType", "id", "id" ) );

        Mockito.when( transportTypeMapper.toTransportTypeDto( Mockito.any() ) )
                .thenReturn( TransportTypeDtoMock.getTransportTypeDtoMock() );

        Mockito.when( transportTypeMapper.toTransportType( Mockito.any() ) )
                .thenReturn( TransportTypeMock.getTransportTypeMock() );

        Exception exception = Assertions.assertThrows(
                ResourceNotFoundException.class, () -> transportTypeService.createTransportType( TransportTypeDtoMock.getTransportTypeDtoMock() ) );

        Assertions.assertEquals( ResourceNotFoundException.class, exception.getClass() );
    }
}
