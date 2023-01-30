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


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class TripService {

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
                logger.log(Level.INFO,"Admin");
                throw new Exception("Not Admin");
            }
        }
        else {
            throw new Exception("User not found");
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

    public List<TripResponse> getAllTripsByWhereFrom(String whereFrom) {
        return tripRepository.findAllByWhereFrom(whereFrom).stream().map(tripConverter::convert).collect(Collectors.toList());
    }
    public List<TripResponse> getAllTripsByVehicleTypeBus() {
        return tripRepository.findAllByVehicleTypeBus(VehicleType.BUS).stream().map(tripConverter::convert).collect(Collectors.toList());
    }
    public List<TripResponse> getAllTripsByVehicleTypePlane() {
        return tripRepository.findAllByVehicleTypePlane(VehicleType.PLANE).stream().map(tripConverter::convert).collect(Collectors.toList());
    }
    public List<TripResponse> getAllTripsByDate(LocalDate date) {
        return tripRepository.findAllByDepartureDate(date.atStartOfDay()).stream().map(tripConverter::convert).collect(Collectors.toList());
    }
}
