package com.ticketreservation.repository;


import com.ticketreservation.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    List<Ticket> findAllByUserId(int id);
    long countByTotalTicketSales(Long totalSales);
    long countByTotalPrice(Long totalPrice);


}
