package com.cloud.microservices.travelservice.external.service.impl;

import com.cloud.dependencies.dtoservice.dto.actorservice.DriverDto;
import com.cloud.dependencies.dtoservice.dto.actorservice.OwnerDto;
import com.cloud.dependencies.dtoservice.dto.actorservice.UserDto;
import com.cloud.microservices.travelservice.external.feignclients.ActorFeignClient;
import com.cloud.microservices.travelservice.external.service.ActorService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    ActorFeignClient actorFeignClient;

    @CircuitBreaker(name = "actorService", fallbackMethod = "fallbackGetUserById")
    public UserDto getUserById(String userId) {
        log.info( "Start external service :: getUserById: [{}]", userId );

        return actorFeignClient.getUserById( userId );
    }

    public UserDto fallbackGetUserById(String userId, Throwable throwable) {
        log.info( "Start external service :: fallbackGetUserById: [{}, {}]", userId, throwable );

        return new UserDto();
    }

    @CircuitBreaker(name = "actorService", fallbackMethod = "fallbackGetDriverById")
    public DriverDto getDriverById(String driverId) {
        log.info( "Start external service :: getDriverById: [{}]", driverId );

        return actorFeignClient.getDriverById( driverId );
    }

    public DriverDto fallbackGetDriverById(String driverId, Throwable throwable) {
        log.info( "Start external service :: fallbackGetDriverById: [{}, {}]", driverId, throwable );

        return new DriverDto();
    }

    @CircuitBreaker(name = "actorService", fallbackMethod = "fallbackGetOwnerById")
    public OwnerDto getOwnerById(String ownerId) {
        log.info( "Start external service :: getOwnerById: [{}]", ownerId );

        return actorFeignClient.getOwnerById( ownerId );
    }

    public OwnerDto fallbackGetOwnerById(String ownerId, Throwable throwable) {
        log.info( "Start external service :: fallbackGetOwnerById: [{}, {}]", ownerId, throwable );

        return new OwnerDto();
    }

}
