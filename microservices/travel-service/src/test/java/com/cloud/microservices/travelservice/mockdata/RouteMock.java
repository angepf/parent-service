package com.cloud.microservices.travelservice.mockdata;

import com.cloud.microservices.travelservice.datajpa.entity.Route;

public class RouteMock {

    public static Route getRouteMock(){
        return Route.builder()
                .id( 1 )
                .routeFrom( "CUENCA CENTRO" )
                .routeTo( "LOJA SUR" )
                .userId( "0000000001" )
                .build();
    }
}
