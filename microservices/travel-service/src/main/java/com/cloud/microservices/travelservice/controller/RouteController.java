package com.cloud.microservices.travelservice.controller;

import com.cloud.dependencies.dtoservice.dto.travelservice.RouteDto;
import com.cloud.microservices.travelservice.service.RouteService;
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
        name = "CRUD REST APIs for Route Service",
        description = "CRUD REST APIs - Create Route, Get Route"
)
@Log4j2
@RestController
@RequestMapping("api/route")
public class RouteController {

    @Autowired
    RouteService routeService;

    @Operation(
            summary = "Get route REST API",
            description = "Get route by id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{routeId}")
    public ResponseEntity<?> getRouteById(@PathVariable Integer routeId) {
        log.info( "Start controller :: getRouteById: [{}]", routeId );
        return new ResponseEntity<>( routeService.getRouteById( routeId ), HttpStatus.OK );
    }

    @Operation(
            summary = "Post route REST API",
            description = "Post route by body to database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATE"
    )
    @PostMapping
    public ResponseEntity<?> createRoute(@Valid @RequestBody RouteDto routeDto) {
        log.info( "Start controller :: createRoute: [{}]", routeDto );
        return new ResponseEntity<>( routeService.createRoute( routeDto ), HttpStatus.OK );
    }

}
