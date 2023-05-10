package com.cloud.microservices.travelservice.datajpa.repository;

import com.cloud.microservices.travelservice.datajpa.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelRepository extends JpaRepository<Travel, Integer> {
}
