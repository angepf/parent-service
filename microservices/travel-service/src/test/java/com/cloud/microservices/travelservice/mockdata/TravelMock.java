package com.cloud.microservices.travelservice.mockdata;

import com.cloud.microservices.travelservice.datajpa.entity.Travel;

import java.sql.Timestamp;
import java.time.Instant;

public class TravelMock {

    public static Travel getTravelMock() {
        return Travel.builder()
                .id( 1L )
                .date( Timestamp.from( Instant.now() ) )
                .routeId( RouteMock.getRouteMock() )
                .time( "09:00" )
                .transportId( TransportMock.getTransportMock() )
                .status( true )
                .userId( "0000000001" )
                .build();
    }
}
