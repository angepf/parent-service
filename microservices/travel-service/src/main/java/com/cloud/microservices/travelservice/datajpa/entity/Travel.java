package com.cloud.microservices.travelservice.datajpa.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Travel {

    @Id
    @SequenceGenerator( name = "seqTravel", initialValue = 161000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTravel")
    private Long id;

    @Column(nullable = false)
    private Timestamp date;

    @Column(nullable = false)
    private String time;

    @ManyToOne
    @JoinColumn(name = "routeId", nullable = false, referencedColumnName = "id")
    private Route routeId;

    @ManyToOne
    @JoinColumn(name = "transportId", nullable = false, referencedColumnName = "licencePlate")
    private Transport transportId;

    @Column(nullable = false)
    private String userId;

    private Boolean status;
}
