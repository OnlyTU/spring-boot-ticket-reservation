package com.ticketreservation.service;


import com.ticketreservation.controller.TripController;
import com.ticketreservation.converter.TripConverter;
import com.ticketreservation.enums.UserType;
import com.ticketreservation.enums.VehicleType;
import com.ticketreservation.model.Trip;
import com.ticketreservation.model.User;
import com.ticketreservation.repository.TripRepository;
import com.ticketreservation.request.TripRequest;
import com.ticketreservation.response.TripResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TripService {
    private static final int MAX_SEATING_CAPACITY_PLANE = 189;
    private static final int MAX_SEATING_CAPACITY_BUS = 45;

    private UserService userService;
    private TicketService ticketService;
    private TripRepository tripRepository;
    private TripConverter tripConverter;

    public TripService(TripRepository tripRepository, TripConverter tripConverter,UserService userService,TicketService ticketService)
    {
        this.tripRepository = tripRepository;
        this.tripConverter = tripConverter;
        this.userService = userService;
        this.ticketService = ticketService;
    }

    public TripResponse createTrip(TripRequest tripRequest) throws Exception{
        Logger logger = Logger.getLogger(TripController.class.getName());
        Optional<User>foundUser =userService.getById(tripRequest.getNo());

        if (foundUser.isPresent()){
            if (UserType.ADMIN.equals(foundUser.get().getUserType())){
                List<Trip> tripList = tripRepository.findAllByTripId(foundUser.get().getId());
                if (VehicleType.BUS.equals(tripRequest.getVehicleType()) && MAX_SEATING_CAPACITY_BUS <= tripList.size()){
                    logger.log(Level.WARNING,"Max seating capacity for bus.");
                    throw new Exception("Max seating capacity for bus.");
                }
                if (VehicleType.PLANE.equals(tripRequest.getVehicleType()) && MAX_SEATING_CAPACITY_PLANE <= tripList.size()){
                    logger.log(Level.WARNING,"Max seating capacity for plane.");
                    throw new Exception("Max seating capacity for plane.");
                }
            }
        }

        Trip trip = tripConverter.convert(tripRequest);

        if (foundUser.isPresent()){
            trip.setUser(foundUser.get());
        }

        Trip savedTrip = tripRepository.save(trip);

        return tripConverter.convert(savedTrip);
    }
    public void delete(Integer id) throws Exception {
        Logger logger = Logger.getLogger(TripController.class.getName());

        var realty = tripRepository.findAllByTripId(id);

        realty.remove(tripRepository.findById(id));
        logger.log(Level.INFO,"Trip canceled");

        throw new Exception("Trip canceled.");
    }

}
