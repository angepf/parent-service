package com.infraestructure.cloud.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayFilter implements GlobalFilter {

    Logger logger = LoggerFactory.getLogger( GatewayFilter.class );

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        logger.info( "filter :: Authorization: [{}]", request.getHeaders().getFirst( "Authorization" ) );

        logger.info( "filter :: Request URI: [{}]", request.getURI() );

        return chain.filter( exchange ).then( Mono.fromRunnable( () -> {
            ServerHttpResponse response = exchange.getResponse();

            logger.info( "filter :: Post filter: [{}]", response.getStatusCode() );
        } ) );
    }
}
