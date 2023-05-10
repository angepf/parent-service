package com.cloud.microservices.travelservice.controller;

import com.cloud.dependencies.dtoservice.exception.ResourceNotFoundException;
import com.cloud.dependencies.dtoservice.mockData.RouteDtoMock;
import com.cloud.microservices.travelservice.service.impl.RouteServiceImpl;
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

@WebMvcTest(RouteController.class)
@TestPropertySource(locations = "classpath:application.properties")
public class RouteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RouteServiceImpl routeService;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString( obj );
    }

    @DisplayName("GetRoute - OK")
    @Test
    void whenCallUrlRouteWithCorrectDataThenOKStatus() throws Exception {
        Mockito.when( routeService.getRouteById( Mockito.any() ) )
                .thenReturn( RouteDtoMock.getRouteDtoMock() );

        mockMvc.perform( get( "/api/route/1" ) )
                .andExpect( status().isOk() );
    }

    @DisplayName("GetRoute - Exception")
    @Test
    void whenCallUrlRouteWithIncorrectDataThenBadRequestStatus() throws Exception {
        Mockito.when( routeService.getRouteById( Mockito.any() ) )
                .thenThrow( new ResourceNotFoundException( "NotFound", "id", "id" ) );

        mockMvc.perform( get( "/api/route/1" ) )
                .andExpect( status().isNotFound() );
    }

    @DisplayName("CreateRoute - OK")
    @Test
    void whenCallUrlRouteSaveWithCorrectDataThenOKStatus() throws Exception {
        Mockito.when( routeService.getRouteById( Mockito.any() ) )
                .thenReturn( RouteDtoMock.getRouteDtoMock() );

        String inputJson = mapToJson( RouteDtoMock.getRouteDtoMock() );

        mockMvc.perform( post( "/api/route" )
                        .accept( MediaType.APPLICATION_JSON )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( inputJson ) )
                .andExpect( status().isOk() );
    }

    @DisplayName("CreateRoute - Exception")
    @Test
    void whenCallUrlRouteSaveWithIncorrectDataThenBadRequestStatus() throws Exception {
        Mockito.when( routeService.getRouteById( Mockito.any() ) )
                .thenThrow( new ResourceNotFoundException( "NotFound", "id", "id" ) );

        String inputJson = mapToJson( new ResourceNotFoundException( "NotFound", "id", "id" ) );

        mockMvc.perform( post( "/api/route" )
                        .accept( MediaType.APPLICATION_JSON )
                        .contentType( MediaType.APPLICATION_JSON )
                        .content( inputJson ) )
                .andExpect( status().isBadRequest() );
    }

}
