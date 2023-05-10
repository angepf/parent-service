package com.cloud.microservices.ticketservice.service;

import com.cloud.dependencies.dtoservice.exception.ResourceNotFoundException;
import com.cloud.dependencies.dtoservice.mockData.PassengerDtoMock;
import com.cloud.dependencies.dtoservice.mockData.TicketDtoMock;
import com.cloud.dependencies.dtoservice.mockData.TravelDtoMock;
import com.cloud.dependencies.dtoservice.mockData.UserDtoMock;
import com.cloud.microservices.ticketservice.datajpa.repository.TicketRepository;
import com.cloud.microservices.ticketservice.external.service.ActorService;
import com.cloud.microservices.ticketservice.external.service.TravelService;
import com.cloud.microservices.ticketservice.mockdata.TicketMock;
import com.cloud.microservices.ticketservice.service.impl.TicketServiceImpl;
import com.cloud.microservices.ticketservice.service.mapper.TicketMapper;
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
public class TicketServiceTest {

    @Mock
    TicketRepository ticketRepository;

    @Mock
    TicketMapper ticketMapper;

    @Mock
    ActorService actorService;

    @Mock
    TravelService travelService;

    @InjectMocks
    TicketServiceImpl ticketService;

    @DisplayName("GetTicket - OK")
    @Test
    void whenGetTicketByIdThenOkResponse() {
        Mockito.when( ticketRepository.findById( Mockito.any() ) )
                .thenReturn( Optional.of( TicketMock.getTicketMock() ) );

        Mockito.when( ticketMapper.toTicketDto( Mockito.any() ) )
                .thenReturn( TicketDtoMock.getTicketDtoMock() );

        Mockito.when( actorService.getUserById( Mockito.any() ) )
                .thenReturn( UserDtoMock.getUserDtoMock() );

        Assertions.assertEquals( TicketMock.getTicketMock().getId(), ticketService.getTicketById( 1L ).getId() );

    }

    @DisplayName("GetTicket - Exception")
    @Test
    void whenGetTicketByIdThenExceptionResponse() {
        Mockito.when( ticketRepository.findById( Mockito.any() ) )
                .thenThrow( new ResourceNotFoundException( "Ticket", "id", "3" ) );

        Mockito.when( ticketMapper.toTicketDto( Mockito.any() ) )
                .thenReturn( TicketDtoMock.getTicketDtoMock() );

        Mockito.when( actorService.getUserById( Mockito.any() ) )
                .thenReturn( UserDtoMock.getUserDtoMock() );

        Exception exception = Assertions.assertThrows(
                ResourceNotFoundException.class, () -> ticketService.getTicketById( 3L ) );

        Assertions.assertEquals( ResourceNotFoundException.class, exception.getClass() );
    }

    @DisplayName("CreateTicket - OK")
    @Test
    void whenCreateTicketByIdThenOkResponse() {
        Mockito.when( ticketRepository.save( Mockito.any() ) )
                .thenReturn( TicketMock.getTicketMock() );

        Mockito.when( ticketMapper.toTicketDto( Mockito.any() ) )
                .thenReturn( TicketDtoMock.getTicketDtoMock() );

        Mockito.when( ticketMapper.toTicket( Mockito.any() ) )
                .thenReturn( TicketMock.getTicketMock() );

        Mockito.when( actorService.getPassengerById( Mockito.any() ) )
                .thenReturn( PassengerDtoMock.getPassengerDtoMock() );

        Mockito.when( travelService.getTravelById( Mockito.any() ) )
                .thenReturn( TravelDtoMock.getTravelDtoMock() );

        Mockito.when( actorService.getUserById( Mockito.any() ) )
                .thenReturn( UserDtoMock.getUserDtoMock() );

        Assertions.assertEquals( TicketDtoMock.getTicketDtoMock().getId(), ticketService.createTicket( TicketDtoMock.getTicketDtoMock() ).getId() );
    }

    @DisplayName("CreateTicket - Exception")
    @Test
    void whenCreateTicketByIdThenException1Response() {
        Mockito.when( ticketRepository.save( Mockito.any() ) )
                .thenReturn( TicketMock.getTicketMock() );

        Mockito.when( ticketMapper.toTicketDto( Mockito.any() ) )
                .thenReturn( TicketDtoMock.getTicketDtoMock() );

        Mockito.when( ticketMapper.toTicket( Mockito.any() ) )
                .thenReturn( TicketMock.getTicketMock() );

        Mockito.when( actorService.getUserById( Mockito.any() ) )
                .thenThrow( new ResourceNotFoundException( "User", "id", "id" ) );

        Exception exception = Assertions.assertThrows(
                ResourceNotFoundException.class, () -> ticketService.createTicket( TicketDtoMock.getTicketDtoMock() ) );

        Assertions.assertEquals( ResourceNotFoundException.class, exception.getClass() );
    }
}
