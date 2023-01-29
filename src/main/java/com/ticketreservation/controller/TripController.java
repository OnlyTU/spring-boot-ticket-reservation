package com.ticketreservation.controller;

import com.ticketreservation.enums.VehicleType;
import com.ticketreservation.request.TripRequest;
import com.ticketreservation.response.TripResponse;
import com.ticketreservation.service.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/trips")
public class TripController {

    private TripService tripService;
    public TripController(TripService tripService){
        this.tripService = tripService;
    }

    @GetMapping(value = "/{whereFrom}")
    public ResponseEntity<List<TripResponse>> getAllTripsByWhereFrom(@PathVariable String whereFrom){
        return null;
    }

    @GetMapping(value = "/vehicleType/bus")
    public ResponseEntity<List<TripResponse>>getAllTripsByVehicleTypeBus(){
        return null;
    }

    @GetMapping(value = "/vehicleType/plane")
    public ResponseEntity<List<TripResponse>>getAllTripsByVehicleTypePlane(){
        return null;
    }

    @GetMapping(value = "/{date}")
    public ResponseEntity<List<TripResponse>>getAllTripsByVehicleTypes(@PathVariable String date){
        return null;
    }
    @PostMapping
    public ResponseEntity<TripResponse> create(@RequestBody TripRequest tripRequest) throws Exception{
        TripResponse trip = tripService.createTrip(tripRequest);
        return new ResponseEntity<>(trip, HttpStatus.CREATED);
    }
    @DeleteMapping(value = "/{tripId}")
    public ResponseEntity <Integer> delete(@PathVariable Integer tripId) throws Exception {
        tripService.delete(tripId);
        return ResponseEntity.ok(tripId);
    }
}
