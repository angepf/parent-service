package com.cloud.microservices.ticketservice.external.service;

import com.cloud.dependencies.dtoservice.dto.travelservice.TravelDto;

public interface TravelService {

    TravelDto getTravelById(Long id);

    TravelDto fallbackGetTravelById(Long id, Throwable th);

}
