package com.cloud.microservices.travelservice.mockdata;

import com.cloud.microservices.travelservice.datajpa.entity.TransportType;

public class TransportTypeMock {

    public static TransportType getTransportTypeMock() {
        return TransportType.builder()
                .id( 1 )
                .description( "BUSETA" )
                .passengers( 12D )
                .build();
    }
}
