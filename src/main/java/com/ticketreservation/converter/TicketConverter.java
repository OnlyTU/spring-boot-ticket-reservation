package com.ticketreservation.converter;

import com.ticketreservation.model.Ticket;
import com.ticketreservation.request.TicketRequest;
import com.ticketreservation.response.TicketResponse;
import org.springframework.stereotype.Component;

@Component
public class TicketConverter {

    public Ticket convert(TicketRequest ticketRequest){
        Ticket ticket = new Ticket();
        ticket.setId(ticketRequest.getNo());
        ticket.setTrip(ticketRequest.getTrip());
        ticket.setMaleQuantity(ticketRequest.getMaleQuantity());
        ticket.setMaleQuantity(ticketRequest.getFemaleQuantity());
        ticket.setPaymentType(ticketRequest.getPaymentType());
        return ticket;
    }

    public TicketResponse convert(Ticket ticket){
        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setWhereFrom(ticket.getTrip().getWhereFrom());
        ticketResponse.setToWhere(ticket.getTrip().getToWhere());
        ticketResponse.setPrice(ticket.getTrip().getPrice());
        ticketResponse.setDate(ticket.getCreateDate());
        ticketResponse.setVehicleType(ticket.getVehicleType());
        return ticketResponse;
    }
}
