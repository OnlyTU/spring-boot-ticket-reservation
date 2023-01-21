package com.ticketreservation.model;

import java.time.LocalDateTime;

import com.ticketreservation.model.enums.PaymentType;
import com.ticketreservation.model.enums.VehicleType;

public class Ticket {
	
	private int id;
	private int user_id;
	private int trip_id;
	private int male_quantity;
	private int female_quantity;
	private PaymentType paymentType;
	private User user;
	private VehicleType vehicleType;
	
	private LocalDateTime crateDate;
}
