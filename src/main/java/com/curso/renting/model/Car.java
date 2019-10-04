package com.curso.renting.model;

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
@Table(name = "car")
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "number_plate", unique = true, nullable = false)
	private String numberPlate;
	@Column(unique = true, nullable = false)
	private String model;
	@Column(unique = true, nullable = false)
	private String brand;
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
}
