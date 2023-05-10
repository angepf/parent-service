package com.cloud.microservices.travelservice.controller;

import com.cloud.dependencies.dtoservice.dto.travelservice.TransportDto;
import com.cloud.microservices.travelservice.service.TransportService;
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
        name = "CRUD REST APIs for Transport Service",
        description = "CRUD REST APIs - Create Transport, Get Transport"
)
@Log4j2
@RestController
@RequestMapping("api/transport")
public class TransportController {

    @Autowired
    TransportService transportService;

    @Operation(
            summary = "Get transport REST API",
            description = "Get transport by id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{transportId}")
    public ResponseEntity<?> getTransportById(@PathVariable String transportId) {
        log.info( "Start controller :: getTransportById: [{}]", transportId );
        return new ResponseEntity<>( transportService.getTransportById( transportId ), HttpStatus.OK );
    }

    @Operation(
            summary = "Post transport REST API",
            description = "Post transport by body to database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATE"
    )
    @PostMapping
    public ResponseEntity<?> createTransport(@Valid @RequestBody TransportDto transportDto) {
        log.info( "Start controller :: createTransport: [{}]", transportDto );
        return new ResponseEntity<>( transportService.createTransport( transportDto ), HttpStatus.OK );
    }

}
