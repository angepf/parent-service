package com.cloud.microservices.ticketservice.service;

import com.cloud.dependencies.dtoservice.dto.ticketservice.TicketDto;

public interface TicketService {

    TicketDto createTicket(TicketDto ticketDto);

    TicketDto getTicketById(Long id);

}
