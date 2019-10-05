package com.curso.renting.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users_cars")
public class Rent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private Car car;
	
	@NotNull
	@Column(name = "initial_date")
	private LocalDate initDate;
	
	@NotNull
	@Column(name = "final_date")
	private LocalDate finalDate;
	
	@NotNull
	private Double price;
}

//LocalDate.from(new Date().toInstance())
//LocalDate.ofEpochDay(new Date().toInstance().getEpochSecond())