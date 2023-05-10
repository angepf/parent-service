package com.cloud.microservices.travelservice.service.impl;

import com.cloud.dependencies.dtoservice.dto.actorservice.DriverDto;
import com.cloud.dependencies.dtoservice.dto.actorservice.OwnerDto;
import com.cloud.dependencies.dtoservice.dto.actorservice.UserDto;
import com.cloud.dependencies.dtoservice.dto.travelservice.TransportDto;
import com.cloud.dependencies.dtoservice.dto.travelservice.TransportTypeDto;
import com.cloud.dependencies.dtoservice.exception.ResourceNotFoundException;
import com.cloud.microservices.travelservice.datajpa.entity.Transport;
import com.cloud.microservices.travelservice.external.service.ActorService;
import com.cloud.microservices.travelservice.datajpa.repository.TransportRepository;
import com.cloud.microservices.travelservice.service.TransportService;
import com.cloud.microservices.travelservice.service.TransportTypeService;
import com.cloud.microservices.travelservice.service.mapper.TransportMapper;
import com.cloud.microservices.travelservice.util.ValidateFields;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransportServiceImpl implements TransportService {

    TransportRepository transportRepository;

    TransportMapper transportMapper;

    ActorService actorService;

    TransportTypeService transportTypeService;

    @Override
    public TransportDto getTransportById(String transportId) {
        log.info( "Start service :: getTransportById: [{}]", transportId );

        Transport transport = transportRepository.findById( transportId )
                .orElseThrow( () -> new ResourceNotFoundException( "Transport", "id", transportId ) );

        TransportDto transportDto = transportMapper.toTransportDto( transport );

        DriverDto driverDto = actorService.getDriverById( transport.getDriverId() );
        OwnerDto ownerDto = actorService.getOwnerById( transport.getOwnerId() );
        UserDto userDto = actorService.getUserById( transport.getUserId() );

        transportDto.setDriverId( driverDto );
        transportDto.setOwnerId( ownerDto );
        transportDto.setUserId( userDto );
        transport.setStatus( true );

        return transportDto;
    }

    @Override
    public TransportDto createTransport(TransportDto transportDto) {
        log.info( "Start service :: createTransport: [{}]", transportDto );

        TransportTypeDto transportTypeDto = transportTypeService.getTransportTypeById( transportDto.getTransportType().getId() );
        DriverDto driverDto = actorService.getDriverById( transportDto.getDriverId().getId() );
        OwnerDto ownerDto = actorService.getOwnerById( transportDto.getOwnerId().getId() );
        UserDto userDto = actorService.getUserById( transportDto.getUserId().getId() );

        Transport transport = transportMapper.toTransport( transportDto );

        ValidateFields.validateField( "User", transportTypeDto.getId().toString(), transportDto.getTransportType().getId().toString() );

        ValidateFields.validateField( "Driver", driverDto.getId(), transportDto.getDriverId().getId() );
        transport.setDriverId( driverDto.getId() );

        ValidateFields.validateField( "Owner", ownerDto.getId(), transportDto.getOwnerId().getId() );
        transport.setOwnerId( ownerDto.getId() );

        ValidateFields.validateField( "User", userDto.getId(), transportDto.getUserId().getId() );
        transport.setUserId( userDto.getId() );

        transport.setStatus( true );


        transportDto = transportMapper.toTransportDto( transportRepository.save( transport ) );
        transportDto.setTransportType( transportTypeDto );
        transportDto.setDriverId( driverDto );
        transportDto.setOwnerId( ownerDto );
        transportDto.setUserId( userDto );


        return transportDto;
    }
}
