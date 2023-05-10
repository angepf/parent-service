package com.cloud.microservices.travelservice.controller;

import com.cloud.dependencies.dtoservice.exception.ResourceNotFoundException;
import com.cloud.dependencies.dtoservice.mockData.TransportDtoMock;
import com.cloud.microservices.travelservice.service.impl.TransportServiceImpl;
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

@WebMvcTest(TransportController.class)
@TestPropertySource(locations = "classpath:application.properties")
public class TransportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransportServiceImpl transportService;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString( obj );
    }

    @DisplayName("GetTransport - OK")
    @Test
    void whenCallUrlTransportWithCorrectDataThenOKStatus() throws Exception {
        Mockito.when( transportService.getTransportById( Mockito.any() ) )
                .thenReturn( TransportDtoMock.getTransportDtoMock() );

        mockMvc.perform( get( "/api/transport/1" ) )
                .andExpect( status().isOk() );
    }

    @DisplayName("GetTransport - Exception")
    @Test
    void whenCallUrlTransportWithIncorrectDataThenBadRequestStatus() throws Exception {
        Mockito.when( transportService.getTransportById( Mockito.any() ) )
                .thenThrow( new ResourceNotFoundException( "NotFound", "id", "id" ) );

        mockMvc.perform( get( "/api/transport/1" ) )
                .andExpect( status().isNotFound() );
    }

    @DisplayName("CreateTransport - OK")
    @Test
    void whenCallUrlTransportSaveWithCorrectDataThenOKStatus() throws Exception {
        Mockito.when( transportService.getTransportById( Mockito.any() ) )
                .thenReturn( TransportDtoMock.getTransportDtoMock() );

        String inputJson = mapToJson( TransportDtoMock.getTransportDtoMock() );

        mockMvc.perform( post( "/api/transport" )
                        .accept( MediaType.APPLICATION_JSON )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( inputJson ) )
                .andExpect( status().isOk() );
    }

    @DisplayName("CreateTransport - Exception")
    @Test
    void whenCallUrlTransportSaveWithIncorrectDataThenBadRequestStatus() throws Exception {
        Mockito.when( transportService.getTransportById( Mockito.any() ) )
                .thenThrow( new ResourceNotFoundException( "NotFound", "id", "id" ) );

        String inputJson = mapToJson( new ResourceNotFoundException( "NotFound", "id", "id" ) );

        mockMvc.perform( post( "/api/transport" )
                        .accept( MediaType.APPLICATION_JSON )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( inputJson ) )
                .andExpect( status().isBadRequest() );
    }

}
