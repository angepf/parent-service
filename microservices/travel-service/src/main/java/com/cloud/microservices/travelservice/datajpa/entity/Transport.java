package com.cloud.microservices.travelservice.datajpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transport {

    @Id
    private String licencePlate;

    @Column(nullable = false)
    private String ownerId;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String registerNumber;

    @Column(nullable = false)
    private String driverId;

    @ManyToOne
    @JoinColumn(name = "transportType", nullable = false)
    private TransportType transportType;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private boolean status;

}
