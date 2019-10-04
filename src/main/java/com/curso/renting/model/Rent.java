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
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	@ManyToOne(fetch = FetchType.LAZY)
	private Car car;
	@Column(name = "initial_date")
	private LocalDate initDate;
	@Column(name = "final_date")
	private LocalDate finalDate;
	@Column(precision = 2, nullable = false)
	private Double price;
}

//LocalDate.from(new Date().toInstance())
//LocalDate.ofEpochDay(new Date().toInstance().getEpochSecond())