package com.ticketreservation.controller;


import com.ticketreservation.repository.UserRepository;
import com.ticketreservation.request.TicketRequest;
import com.ticketreservation.request.TripRequest;
import com.ticketreservation.request.UserRequest;
import com.ticketreservation.response.TicketResponse;

import com.ticketreservation.service.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tickets")
public class TicketController {
    private TicketService ticketService;


    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<TicketResponse>> getAllByUserId(@PathVariable int id) {
        List<TicketResponse> tickets = ticketService.getAllById(id);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping(value = "/totalSales")
    public ResponseEntity<Long> getAllTotalTicketSales(Long totalSales){
        return ResponseEntity.ok(ticketService.getAllTickets(totalSales));
    }

    @GetMapping(value = "/totalRevenue")
    public ResponseEntity<Long> getTotalSalesRevenue(Long totalRevenue){
        return ResponseEntity.ok(ticketService.getTotalSalesRevenue(totalRevenue));
    }
    @PostMapping
    public ResponseEntity<TicketResponse> create(@RequestBody TicketRequest ticketRequest, @RequestBody TripRequest tripRequest, @RequestBody UserRequest userRequest) throws Exception{
        TicketResponse ticket = ticketService.create(ticketRequest,tripRequest,userRequest);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

}
