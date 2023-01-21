package com.ticketreservation.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ticketreservation.model.enums.RoleType;
import com.ticketreservation.model.enums.UserType;

public class User {
	
	private int id;
	private String name;
	private String surname;
	private String email;
	private String password;
	private String phone_number;
	private UserType userType;
	private RoleType roleType;
	
	private List<Ticket> ticketList = new ArrayList<>();
	private List<Trip> tripList = new ArrayList<>();
	
	private LocalDateTime createDate;
}
