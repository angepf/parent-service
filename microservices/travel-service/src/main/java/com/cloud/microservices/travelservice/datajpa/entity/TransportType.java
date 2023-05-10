package com.cloud.microservices.travelservice.datajpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransportType {

    @Id
    @SequenceGenerator( name = "seqTransportType", initialValue = 6)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTransportType")
    private Integer id;
    private String description;
    private Double passengers;

}
