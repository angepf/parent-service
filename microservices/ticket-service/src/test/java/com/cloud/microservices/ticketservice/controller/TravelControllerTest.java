package com.cloud.microservices.ticketservice.controller;

import com.cloud.dependencies.dtoservice.exception.ResourceNotFoundException;
import com.cloud.dependencies.dtoservice.mockData.TicketDtoMock;
import com.cloud.microservices.ticketservice.service.impl.TicketServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicketController.class)
@TestPropertySource(locations = "classpath:application.properties")
public class TravelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketServiceImpl ticketService;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString( obj );
    }

    @DisplayName("GetTicket - OK")
    @Test
    void whenCallUrlTicketWithCorrectDataThenOKStatus() throws Exception {
        Mockito.when( ticketService.getTicketById( Mockito.any() ) )
                .thenReturn( TicketDtoMock.getTicketDtoMock() );

        mockMvc.perform( get( "/api/ticket/1" ) )
                .andExpect( status().isOk() );
    }

    @DisplayName("GetTicket - Exception")
    @Test
    void whenCallUrlTicketWithIncorrectDataThenBadRequestStatus() throws Exception {
        Mockito.when( ticketService.getTicketById( Mockito.any() ) )
                .thenThrow( new ResourceNotFoundException( "NotFound", "id", "id" ) );

        mockMvc.perform( get( "/api/ticket/1" ) )
                .andExpect( status().isNotFound() );
    }

    @DisplayName("CreateTicket - OK")
    @Test
    void whenCallUrlTicketSaveWithCorrectDataThenOKStatus() throws Exception {
        Mockito.when( ticketService.getTicketById( Mockito.any() ) )
                .thenReturn( TicketDtoMock.getTicketDtoMock() );

        String inputJson = mapToJson( TicketDtoMock.getTicketDtoMock() );

        mockMvc.perform( post( "/api/ticket" )
                        .accept( MediaType.APPLICATION_JSON )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( inputJson ) )
                .andExpect( status().isOk() );
    }

    @DisplayName("CreateTicket - Exception")
    @Test
    void whenCallUrlTicketSaveWithIncorrectDataThenBadRequestStatus() throws Exception {
        Mockito.when( ticketService.getTicketById( Mockito.any() ) )
                .thenThrow( new ResourceNotFoundException( "NotFound", "id", "id" ) );

        String inputJson = mapToJson( new ResourceNotFoundException( "NotFound", "id", "id" ) );

        mockMvc.perform( post( "/api/ticket" )
                        .accept( MediaType.APPLICATION_JSON )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( inputJson ) )
                .andExpect( status().isBadRequest() );
    }

}
