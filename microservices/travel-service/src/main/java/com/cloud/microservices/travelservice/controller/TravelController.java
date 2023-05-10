package com.cloud.microservices.travelservice.controller;

import com.cloud.dependencies.dtoservice.dto.travelservice.TravelDto;
import com.cloud.microservices.travelservice.service.TravelService;
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
        name = "CRUD REST APIs for Travel Service",
        description = "CRUD REST APIs - Create Travel, Get Travel"
)
@Log4j2
@RestController
@RequestMapping("api/travel")
public class TravelController {

    @Autowired
    TravelService travelService;

    @Operation(
            summary = "Get travel REST API",
            description = "Get travel by id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{travelId}")
    public ResponseEntity<?> getTravelById(@PathVariable Integer travelId) {
        log.info( "Start controller :: getTravelById: [{}]", travelId );
        return new ResponseEntity<>( travelService.getTravelById( travelId ), HttpStatus.OK );
    }

    @Operation(
            summary = "Post travel REST API",
            description = "Post travel by body to database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATE"
    )
    @PostMapping
    public ResponseEntity<?> createTravel(@Valid @RequestBody TravelDto travelDto) {
        log.info( "Start controller :: createTravel: [{}]", travelDto );
        return new ResponseEntity<>( travelService.createTravel( travelDto ), HttpStatus.OK );
    }

}
