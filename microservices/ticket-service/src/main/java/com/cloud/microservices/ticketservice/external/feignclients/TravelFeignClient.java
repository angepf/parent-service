package com.cloud.microservices.ticketservice.external.feignclients;

import com.cloud.dependencies.dtoservice.dto.travelservice.TravelDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "api-gateway")
@Headers("Content-Type: application/json")
public interface TravelFeignClient {

    @GetMapping("${travel.url}" + "${travel.get.travelById}")
    TravelDto getTravelById(@PathVariable Long travelId);

}
