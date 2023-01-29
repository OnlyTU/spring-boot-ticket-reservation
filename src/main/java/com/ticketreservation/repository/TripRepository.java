package com.ticketreservation.repository;

import com.ticketreservation.enums.VehicleType;
import com.ticketreservation.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip,String> {
    List<Trip> findAllByVehicleTypeBus(VehicleType bus);
    List<Trip> findAllByVehicleTypePlane(VehicleType plane);
    List<Trip> findAllByDepartureDate(LocalDateTime localDateTime);
    List<Trip>findProvinceByWhereFrom(String whereFrom);
    List<Trip> findAllByTripId(Integer id);

    List<Trip>findById(Integer id);
}
