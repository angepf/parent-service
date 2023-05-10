package com.cloud.microservices.travelservice.datajpa.repository;

import com.cloud.microservices.travelservice.datajpa.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends JpaRepository<Transport, String> {
}
