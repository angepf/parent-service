package com.cloud.microservices.travelservice.service;

import com.cloud.dependencies.dtoservice.dto.travelservice.TravelDto;

public interface TravelService {

    TravelDto getTravelById(Integer travelId);

    TravelDto createTravel(TravelDto travelDto);

}
