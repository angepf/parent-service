package com.cloud.microservices.ticketservice.external.service.impl;

import com.cloud.dependencies.dtoservice.dto.actorservice.PassengerDto;
import com.cloud.dependencies.dtoservice.dto.actorservice.UserDto;
import com.cloud.microservices.ticketservice.external.feignclients.ActorFeignClient;
import com.cloud.microservices.ticketservice.external.service.ActorService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    ActorFeignClient actorFeignClient;

    @CircuitBreaker(name = "actorService", fallbackMethod = "fallbackGetPassengerById")
    public PassengerDto getPassengerById(String userId) {
        log.info( "Start external service :: getPassengerById: [{}]", userId );

        return actorFeignClient.getPassengerById( userId );
    }

    public PassengerDto fallbackGetPassengerById(String userId, Throwable throwable) {
        log.info( "Start external service :: fallbackGetPassengerById: [{}, {}]", userId, throwable );

        return new PassengerDto();
    }

    @CircuitBreaker(name = "actorService", fallbackMethod = "fallbackGetUserById")
    public UserDto getUserById(String userId) {
        log.info( "Start external service :: getUserById: [{}]", userId );

        return actorFeignClient.getUserById( userId );
    }

    public UserDto fallbackGetUserById(String userId, Throwable throwable) {
        log.info( "Start external service :: fallbackGetUserById: [{}, {}]", userId, throwable );

        return new UserDto();
    }
}
