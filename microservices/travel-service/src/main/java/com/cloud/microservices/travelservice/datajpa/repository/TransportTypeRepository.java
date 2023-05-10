package com.cloud.microservices.travelservice.datajpa.repository;

import com.cloud.microservices.travelservice.datajpa.entity.TransportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportTypeRepository extends JpaRepository<TransportType, Integer> {
}
