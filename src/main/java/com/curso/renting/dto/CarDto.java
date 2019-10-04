package com.curso.renting.dto;

import javax.validation.constraints.NotNull;

import com.curso.renting.model.User;

public class CarDto {
	private Integer id;
	@NotNull(message = "La matrícula no puede ser nula")
	private String numberPlate;
	@NotNull(message = "El modelo no puede ser nulo")
	private String model;
	@NotNull(message = "La marca no puede ser nula")
	private String brand;
	private User user;
	
	public CarDto() {}
	
	public CarDto(Integer id, String numberPlate, String model,String brand, User user) {
		this.id = id;
		this.numberPlate = numberPlate;
		this.model = model;
		this.brand = brand;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}
	
	public String getNumberPlate() {
		return numberPlate;
	}
	
	public String getModel() {
		return model;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public User getUser() {
		return user;
	}
}
