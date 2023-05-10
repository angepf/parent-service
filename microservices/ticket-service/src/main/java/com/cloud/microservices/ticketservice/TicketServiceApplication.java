package com.cloud.microservices.ticketservice;

import com.cloud.microservices.ticketservice.configuration.ActorProperties;
import com.cloud.microservices.ticketservice.configuration.TravelProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableConfigurationProperties({ActorProperties.class, TravelProperties.class})
@EnableFeignClients
@EnableDiscoveryClient
@OpenAPIDefinition(
        info = @Info(
                title = "Spring Ticket Service",
                description = "Spring Ticket Service",
                version = "v1.0",
                contact = @Contact(
                        name = "Angélica Pinos",
                        email = "bpinos@est.ups.edu.ec",
                        url = "ups.edu.ec"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "ups.edu.ec"
                )
        )
)
public class TicketServiceApplication {

    public static void main(String[] args) {
		SpringApplication.run( TicketServiceApplication.class, args );
    }

}
