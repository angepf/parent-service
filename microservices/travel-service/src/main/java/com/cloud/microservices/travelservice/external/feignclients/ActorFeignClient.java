package com.cloud.microservices.travelservice.external.feignclients;

import com.cloud.dependencies.dtoservice.dto.actorservice.DriverDto;
import com.cloud.dependencies.dtoservice.dto.actorservice.OwnerDto;
import com.cloud.dependencies.dtoservice.dto.actorservice.UserDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "api-gateway")
@Headers("Content-Type: application/json")
public interface ActorFeignClient {

    @GetMapping("${actor.url}" + "${actor.get.userById}")
    UserDto getUserById(@PathVariable String userId);

    @GetMapping("${actor.url}" + "${actor.get.driverById}")
    DriverDto getDriverById(@PathVariable String driverId);

    @GetMapping("${actor.url}" + "${actor.get.ownerById}")
    OwnerDto getOwnerById(@PathVariable String ownerId);
}
