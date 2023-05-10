package com.cloud.microservices.ticketservice.external.feignclients;

import com.cloud.dependencies.dtoservice.dto.actorservice.PassengerDto;
import com.cloud.dependencies.dtoservice.dto.actorservice.UserDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "api-gateway")
@Headers("Content-Type: application/json")
public interface ActorFeignClient {

    @GetMapping("${actor.url}" + "${actor.get.passengerById}")
    PassengerDto getPassengerById(@PathVariable String passengerId);

    @GetMapping("${actor.url}" + "${actor.get.userById}")
    UserDto getUserById(@PathVariable String userId);
}
