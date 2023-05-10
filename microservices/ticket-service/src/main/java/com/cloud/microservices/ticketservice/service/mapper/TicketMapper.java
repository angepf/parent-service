package com.cloud.microservices.ticketservice.service.mapper;

import com.cloud.dependencies.dtoservice.dto.ticketservice.TicketDto;
import com.cloud.microservices.ticketservice.datajpa.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(target = "travelId", source = "travelId", ignore = true)
    @Mapping(target = "passengerId", source = "passengerId", ignore = true)
    @Mapping(target = "userId", source = "userId", ignore = true)
    TicketDto toTicketDto(Ticket ticket);

    @Mapping(target = "travelId", source = "travelId", ignore = true)
    @Mapping(target = "passengerId", source = "passengerId", ignore = true)
    @Mapping(target = "userId", source = "userId", ignore = true)
    Ticket toTicket(TicketDto ticketDto);

}