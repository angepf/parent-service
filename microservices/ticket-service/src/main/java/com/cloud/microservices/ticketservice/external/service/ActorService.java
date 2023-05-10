package com.cloud.microservices.ticketservice.external.service;

import com.cloud.dependencies.dtoservice.dto.actorservice.PassengerDto;
import com.cloud.dependencies.dtoservice.dto.actorservice.UserDto;

public interface ActorService {

    PassengerDto getPassengerById (String id);

    PassengerDto fallbackGetPassengerById (String id, Throwable th);

    UserDto getUserById(String userId);

    UserDto fallbackGetUserById(String userId, Throwable th);

}
