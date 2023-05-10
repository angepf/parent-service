package com.cloud.microservices.ticketservice.service.impl;

import com.cloud.dependencies.dtoservice.dto.actorservice.PassengerDto;
import com.cloud.dependencies.dtoservice.dto.actorservice.UserDto;
import com.cloud.dependencies.dtoservice.dto.ticketservice.TicketDto;
import com.cloud.dependencies.dtoservice.dto.travelservice.RouteDto;
import com.cloud.dependencies.dtoservice.dto.travelservice.TransportDto;
import com.cloud.dependencies.dtoservice.dto.travelservice.TravelDto;
import com.cloud.dependencies.dtoservice.exception.ResourceNotFoundException;
import com.cloud.microservices.ticketservice.datajpa.entity.Ticket;
import com.cloud.microservices.ticketservice.datajpa.repository.TicketRepository;
import com.cloud.microservices.ticketservice.external.service.ActorService;
import com.cloud.microservices.ticketservice.external.service.TravelService;
import com.cloud.microservices.ticketservice.service.TicketService;
import com.cloud.microservices.ticketservice.service.mapper.TicketMapper;
import com.cloud.microservices.ticketservice.util.ValidateFields;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketServiceImpl implements TicketService {

    TicketRepository ticketRepository;

    ActorService actorService;

    TravelService travelService;

    TicketMapper ticketMapper;

    @Override
    public TicketDto getTicketById(Long ticketId) {
        log.info( "Start service :: getTicketById: [{}]", ticketId );

        Ticket ticket = ticketRepository.findById( ticketId )
                .orElseThrow( () -> new ResourceNotFoundException( "Ticket", "id", ticketId.toString() ) );

        TicketDto ticketDto = ticketMapper.toTicketDto( ticket );

        TravelDto travelDto = travelService.getTravelById( ticket.getTravelId() );
        PassengerDto passengerDto = actorService.getPassengerById( ticket.getPassengerId() );
        UserDto userDto = actorService.getUserById( ticket.getUserId() );

        ticketDto.setTravelId( travelDto );
        ticketDto.setPassengerId( passengerDto );
        ticketDto.setUserId( userDto );

        return ticketDto;
    }

    public TicketDto createTicket(TicketDto ticketDto) {
        log.info( "Start service :: createTicket: [{}]", ticketDto );


        Ticket ticket = ticketMapper.toTicket( ticketDto );

        PassengerDto passengerDto = actorService.getPassengerById( ticketDto.getPassengerId().getId() );
        UserDto userDto = actorService.getUserById( ticketDto.getUserId().getId() );
        TravelDto travelDto = travelService.getTravelById( ticketDto.getTravelId().getId() );

        ValidateFields.validateField( "Passenger", passengerDto.getId(), ticketDto.getPassengerId().getId() );
        ticket.setPassengerId( passengerDto.getId() );

        ValidateFields.validateField( "Travel", travelDto.getId().toString(), ticketDto.getTravelId().getId().toString() );
        ticket.setTravelId( travelDto.getId() );

        ValidateFields.validateField( "User", userDto.getId(), ticketDto.getUserId().getId() );
        ticket.setUserId( userDto.getId() );

        ticket.setIsUsed( false );

        ticketDto = ticketMapper.toTicketDto( ticketRepository.save( ticket ) );
        ticketDto.setPassengerId( passengerDto );
        ticketDto.setTravelId( travelDto );
        ticketDto.setUserId( userDto );

        return ticketDto;
    }


}
