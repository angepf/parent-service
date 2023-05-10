package com.cloud.microservices.travelservice.datajpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Route {

    @Id
    @SequenceGenerator(name = "seqRoute", initialValue = 34)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqRoute")
    private Integer id;

    @Column(nullable = false)
    private String routeFrom;

    @Column(nullable = false)
    private String routeTo;

    private String userId;

}
