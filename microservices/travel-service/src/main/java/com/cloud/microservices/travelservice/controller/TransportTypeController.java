package com.cloud.microservices.travelservice.controller;

import com.cloud.dependencies.dtoservice.dto.travelservice.TransportTypeDto;
import com.cloud.microservices.travelservice.service.TransportTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST APIs for TransportType Service",
        description = "CRUD REST APIs - Create TransportType, Get TransportType"
)
@Log4j2
@RestController
@RequestMapping("api/transportType")
public class TransportTypeController {

    @Autowired
    TransportTypeService transportTypeService;

    @Operation(
            summary = "Get transportType REST API",
            description = "Get transportType by id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{transportTypeId}")
    public ResponseEntity<?> getTransportTypeById(@PathVariable Integer transportTypeId) {
        log.info( "Start controller :: getTransportTypeById: [{}]", transportTypeId );
        return new ResponseEntity<>( transportTypeService.getTransportTypeById( transportTypeId ), HttpStatus.OK );
    }

    @Operation(
            summary = "Post transportType REST API",
            description = "Post transportType by body to database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATE"
    )
    @PostMapping
    public ResponseEntity<?> createTransportType(@Valid @RequestBody TransportTypeDto transportTypeDto) {
        log.info( "Start controller :: createTransportType: [{}]", transportTypeDto );
        return new ResponseEntity<>( transportTypeService.createTransportType( transportTypeDto ), HttpStatus.OK );
    }

}
