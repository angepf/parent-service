package com.cloud.microservices.ticketservice.mockdata;

import com.cloud.microservices.ticketservice.datajpa.entity.Ticket;

import java.sql.Timestamp;
import java.time.Instant;

public class TicketMock {

    public static Ticket getTicketMock() {
        return Ticket.builder()
                .id( 1L )
                .date( Timestamp.from( Instant.now() ) )
                .passengerId( "0000000002" )
                .travelId( 1L )
                .userId( "0000000001" )
                .build();
    }
}
