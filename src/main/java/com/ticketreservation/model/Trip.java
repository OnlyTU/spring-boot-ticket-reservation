package com.ticketreservation.model;

import com.ticketreservation.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
@Table(name="trips")
public class Trip {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id",nullable = false)
	private int id;
	@Column(name = "where_from",nullable = false)
	private String whereFrom;
	@Column(name = "to_where",nullable = false)
	private String toWhere;
	@Column(name = "price",nullable = false)
	private Integer price;
	@Column(name = "departure_date",nullable = false)
	private LocalDateTime departureDate;
	@Column(name = "seating_capacity",nullable = false)
	private Integer seatingCapacity;
	@Column(name = "occupied_capacity",nullable = false)
	private Integer occupiedCapacity;
	@ManyToOne
	@JoinColumn(name="admin_id",referencedColumnName = "id",nullable = false)
	private User user;
	@Enumerated(EnumType.STRING)
	private VehicleType vehicleType;

}
