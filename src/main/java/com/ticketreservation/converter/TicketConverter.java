package com.ticketreservation.converter;

import com.ticketreservation.model.Ticket;
import com.ticketreservation.model.Trip;
import com.ticketreservation.request.TicketRequest;
import com.ticketreservation.response.TicketResponse;
import org.springframework.stereotype.Component;

@Component
public class TicketConverter {

    public Ticket convert(TicketRequest ticketRequest){
        Ticket ticket = new Ticket();
        ticket.setId(ticket.getId());
        ticket.setTrip(ticket.getTrip());
        ticket.setMaleQuantity(ticket.getMaleQuantity());
        ticket.setMaleQuantity(ticket.getFemaleQuantity());
        ticket.setPaymentType(ticket.getPaymentType());
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
