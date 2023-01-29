package com.ticketreservation.converter;

import com.ticketreservation.model.Trip;
import com.ticketreservation.request.TripRequest;
import com.ticketreservation.response.TripResponse;
import org.springframework.stereotype.Component;

@Component
public class TripConverter {


    public TripResponse convert(Trip trip){
        TripResponse response = new TripResponse();
        response.setNo(trip.getId());
        response.setWhereFrom(trip.getWhereFrom());
        response.setToWhere(trip.getToWhere());
        response.setPrice(trip.getPrice());
        response.setDate(trip.getDepartureDate());
        response.setSeatingCapacity(trip.getSeatingCapacity());
        response.setOccupiedCapacity(trip.getOccupiedCapacity());
        response.setVehicleType(trip.getVehicleType());
        return response;
    }

    public Trip convert(TripRequest tripRequest){
        Trip trip = new Trip();
        trip.setId(tripRequest.getNo());
        trip.setWhereFrom(tripRequest.getWhereFrom());
        trip.setToWhere(tripRequest.getToWhere());
        trip.setPrice(tripRequest.getPrice());
        trip.setDepartureDate(tripRequest.getDate());
        trip.setSeatingCapacity(trip.getSeatingCapacity());
        trip.setOccupiedCapacity(trip.getOccupiedCapacity());
        trip.setVehicleType(tripRequest.getVehicleType());
        return trip;
    }

}
