package com.cloud.microservices.ticketservice.external.service.impl;

import com.cloud.dependencies.dtoservice.dto.actorservice.UserDto;
import com.cloud.dependencies.dtoservice.dto.travelservice.TravelDto;
import com.cloud.microservices.ticketservice.external.feignclients.TravelFeignClient;
import com.cloud.microservices.ticketservice.external.service.TravelService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class TravelServiceImpl implements TravelService {

    @Autowired
    TravelFeignClient travelFeignClient;

    @CircuitBreaker(name = "travelService", fallbackMethod = "fallbackGetTravelById")
    public TravelDto getTravelById(Long travelId) {
        log.info( "Start external service :: getTravelById: [{}]", travelId );

        return travelFeignClient.getTravelById( travelId );
    }

    public TravelDto fallbackGetTravelById(Long travelId, Throwable throwable) {
        log.info( "Start external service :: fallbackGetTravelById: [{}, {}]", travelId, throwable );

        return new TravelDto();
    }
}
