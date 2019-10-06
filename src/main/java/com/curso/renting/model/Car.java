package com.curso.renting.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Column(unique = true, name = "number_plate")
	private String numberPlate;
	
	@NotNull
	@Column(unique = true)
	private String model;
	
	@NotNull
	@Column(unique = true)
	private String brand;
	
	@JsonBackReference
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
	private List<Rent> rents;
	
	@NotNull
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
}
