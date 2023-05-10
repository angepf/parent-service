package com.cloud.microservices.travelservice.service.impl;

import com.cloud.dependencies.dtoservice.exception.ResourceNotFoundException;
import com.cloud.dependencies.dtoservice.dto.travelservice.TransportTypeDto;
import com.cloud.microservices.travelservice.datajpa.entity.TransportType;
import com.cloud.microservices.travelservice.datajpa.repository.TransportTypeRepository;
import com.cloud.microservices.travelservice.service.TransportTypeService;
import com.cloud.microservices.travelservice.service.mapper.TransportTypeMapper;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransportTypeServiceImpl implements TransportTypeService {

    TransportTypeRepository transportTypeRepository;

    TransportTypeMapper transportTypeMapper;

    @Override
    public TransportTypeDto getTransportTypeById(Integer transportTypeId) {
        log.info( "Start service :: getTransportTypeById: [{}]", transportTypeId );

        return transportTypeMapper.toTransportTypeDto( transportTypeRepository.findById( transportTypeId )
                .orElseThrow( () -> new ResourceNotFoundException( "TransportType", "id", transportTypeId.toString() ) ) );
    }

    @Override
    public TransportTypeDto createTransportType(TransportTypeDto transportTypeDto) {
        log.info( "Service :: createTransportType: [{}]", transportTypeDto );

        TransportType transportType = transportTypeMapper.toTransportType( transportTypeDto );
        transportTypeDto = transportTypeMapper.toTransportTypeDto( transportTypeRepository.save( transportType ) );

        return transportTypeDto;
    }
}
