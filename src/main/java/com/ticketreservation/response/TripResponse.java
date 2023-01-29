package com.ticketreservation.response;

import com.ticketreservation.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TripResponse {
    private Integer no;
    private String whereFrom;
    private String toWhere;
    private Integer price;
    private LocalDateTime date;
    private Integer seatingCapacity;
    private Integer occupiedCapacity;
    private VehicleType vehicleType;
}
