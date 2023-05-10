package com.cloud.microservices.travelservice.controller;

import com.cloud.dependencies.dtoservice.exception.ResourceNotFoundException;
import com.cloud.dependencies.dtoservice.mockData.TravelDtoMock;
import com.cloud.microservices.travelservice.service.impl.TravelServiceImpl;
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

@WebMvcTest(TravelController.class)
@TestPropertySource(locations = "classpath:application.properties")
public class TravelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TravelServiceImpl travelService;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString( obj );
    }

    @DisplayName("GetTravel - OK")
    @Test
    void whenCallUrlTravelWithCorrectDataThenOKStatus() throws Exception {
        Mockito.when( travelService.getTravelById( Mockito.any() ) )
                .thenReturn( TravelDtoMock.getTravelDtoMock() );

        mockMvc.perform( get( "/api/travel/1" ) )
                .andExpect( status().isOk() );
    }

    @DisplayName("GetTravel - Exception")
    @Test
    void whenCallUrlTravelWithIncorrectDataThenBadRequestStatus() throws Exception {
        Mockito.when( travelService.getTravelById( Mockito.any() ) )
                .thenThrow( new ResourceNotFoundException( "NotFound", "id", "id" ) );

        mockMvc.perform( get( "/api/travel/1" ) )
                .andExpect( status().isNotFound() );
    }

    @DisplayName("CreateTravel - OK")
    @Test
    void whenCallUrlTravelSaveWithCorrectDataThenOKStatus() throws Exception {
        Mockito.when( travelService.getTravelById( Mockito.any() ) )
                .thenReturn( TravelDtoMock.getTravelDtoMock() );

        String inputJson = mapToJson( TravelDtoMock.getTravelDtoMock() );

        mockMvc.perform( post( "/api/travel" )
                        .accept( MediaType.APPLICATION_JSON )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( inputJson ) )
                .andExpect( status().isOk() );
    }

    @DisplayName("CreateTravel - Exception")
    @Test
    void whenCallUrlTravelSaveWithIncorrectDataThenBadRequestStatus() throws Exception {
        Mockito.when( travelService.getTravelById( Mockito.any() ) )
                .thenThrow( new ResourceNotFoundException( "NotFound", "id", "id" ) );

        String inputJson = mapToJson( new ResourceNotFoundException( "NotFound", "id", "id" ) );

        mockMvc.perform( post( "/api/travel" )
                        .accept( MediaType.APPLICATION_JSON )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( inputJson ) )
                .andExpect( status().isBadRequest() );
    }

}
