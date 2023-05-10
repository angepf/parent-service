package com.cloud.microservices.ticketservice.datajpa.repository;

import com.cloud.microservices.ticketservice.datajpa.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
