package com.cloud.microservices.travelservice.service;

import com.cloud.dependencies.dtoservice.dto.travelservice.TransportDto;

public interface TransportService {

    TransportDto getTransportById(String transportId);

    TransportDto createTransport(TransportDto transportDto);

}
