package com.cloud.microservices.ticketservice.datajpa.entity;

import lombok.*;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @Id
    @SequenceGenerator( name = "seqTicket", initialValue = 161000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqTicket")
    private Long id;

    @Column(nullable = false)
    private Timestamp date;

    @Column(nullable = false)
    private Long travelId;

    @Column(nullable = false)
    private String passengerId;

    @Column(nullable = false)
    private Boolean isUsed;

    @Column(nullable = false)
    private String userId;

}
