package com.cloud.microservices.travelservice.service;

import com.cloud.dependencies.dtoservice.dto.travelservice.TransportTypeDto;

public interface TransportTypeService {

    TransportTypeDto getTransportTypeById(Integer transportTypeId);

    TransportTypeDto createTransportType(TransportTypeDto transportTypeDto);

}
