package com.cloud.microservices.ticketservice.controller;

import com.cloud.dependencies.dtoservice.dto.ticketservice.TicketDto;
import com.cloud.microservices.ticketservice.service.TicketService;
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
        name = "CRUD REST APIs for Ticket Service",
        description = "CRUD REST APIs - Create Ticket, Get Ticket"
)
@Log4j2
@RestController
@RequestMapping("api/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;

    @Operation(
            summary = "Get ticket REST API",
            description = "Get ticket by id from database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 OK"
    )
    @GetMapping("/{ticketId}")
    public ResponseEntity<?> getTicketById(@PathVariable Long ticketId) {
        log.info( "Start controller :: getTicketById: [{}]", ticketId );
        return new ResponseEntity<>( ticketService.getTicketById( ticketId ), HttpStatus.OK );
    }

    @Operation(
            summary = "Post ticket REST API",
            description = "Post ticket by body to database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATE"
    )
    @PostMapping
    public ResponseEntity<?> createTicket(@Valid @RequestBody TicketDto ticketDto) {
        log.info( "Start controller :: createTicket: [{}]", ticketDto );
        return new ResponseEntity<>( ticketService.createTicket( ticketDto ), HttpStatus.OK );
    }

}
