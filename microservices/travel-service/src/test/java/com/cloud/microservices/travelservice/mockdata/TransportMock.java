package com.cloud.microservices.travelservice.mockdata;

import com.cloud.microservices.travelservice.datajpa.entity.Transport;

public class TransportMock {

    public static Transport getTransportMock() {
        return Transport.builder()
                .licencePlate( "ADF9873" )
                .color( "GRISSS" )
                .transportType( TransportTypeMock.getTransportTypeMock() )
                .companyName( "TRANSPORTES AMERICA" )
                .year( "2009" )
                .ownerId( "0000000004" )
                .driverId( "0000000005" )
                .registerNumber( "9382322" )
                .status( true )
                .userId( "0000000001" )
                .build();
    }

}
