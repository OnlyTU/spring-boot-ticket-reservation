package com.ticketreservation.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.ticketreservation.enums.UserType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id",nullable = false)
	private Integer id;
	@Column(name = "name")
	private String name;
	@Column(name = "surname")
	private String surname;
	@Column(name = "email",nullable = false)
	private String email;
	@Column(name = "password",nullable = false)
	private String password;
	@Column(name = "phone_number")
	private String phoneNumber;
	@Enumerated(EnumType.STRING)
	private UserType userType;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime createDate;

	@OneToMany(mappedBy = "user" ,fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Ticket> ticketList = new ArrayList<>();

}
