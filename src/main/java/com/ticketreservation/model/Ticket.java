package com.ticketreservation.model;

import java.time.LocalDateTime;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.ticketreservation.enums.PaymentType;
import com.ticketreservation.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="tickets")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "male_quantity",nullable = false)
	private Integer maleQuantity;
	@Column(name = "female_quantity",nullable = false)
	private Integer femaleQuantity;
	@Enumerated(EnumType.STRING)
	private PaymentType paymentType;
	@ManyToOne
	@JoinColumn(name="user_id",referencedColumnName = "id",nullable = false)
	private User user;

	@OneToOne
	@JoinColumn(name="trip_id",referencedColumnName = "id",nullable = false)
	private Trip trip;

	@Enumerated(EnumType.STRING)
	private VehicleType vehicleType;

	@JsonSerialize(using=LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@Column(name = "create_date")
	private LocalDateTime crateDate;
}
