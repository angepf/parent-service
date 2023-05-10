package com.cloud.microservices.travelservice.external.service;

import com.cloud.dependencies.dtoservice.dto.actorservice.DriverDto;
import com.cloud.dependencies.dtoservice.dto.actorservice.OwnerDto;
import com.cloud.dependencies.dtoservice.dto.actorservice.UserDto;

public interface ActorService {

    UserDto getUserById(String userId);

    UserDto fallbackGetUserById(String userId, Throwable th);

    DriverDto getDriverById(String driverId);

    DriverDto fallbackGetDriverById(String driverId, Throwable th);

    OwnerDto getOwnerById(String ownerId);

    OwnerDto fallbackGetOwnerById(String ownerId, Throwable th);

}
