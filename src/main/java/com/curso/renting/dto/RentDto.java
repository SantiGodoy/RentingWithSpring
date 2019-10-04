package com.curso.renting.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.curso.renting.model.Car;
import com.curso.renting.model.User;

public class RentDto {
	private Integer id;
	private User user;
	private Car car;
	private LocalDate initDate;
	private LocalDate finalDate;
	@NotNull(message = "El precio no puede ser nulo")
	private Double price;
	
	public RentDto() {}
	
	public RentDto(Integer id, User user, Car car, LocalDate initDate,
			LocalDate finalDate, Double price) {
		this.id = id;
		this.user = user;
		this.car = car;
		this.initDate = initDate;
		this.finalDate = finalDate;
		this.price = price;
	}

	public Integer getId() {
		return id;
	}
	
	public User getUser() {
		return user;
	}
	
	public Car getCar() {
		return car;
	}
	
	public LocalDate getInitDate() {
		return initDate;
	}
	
	public LocalDate getFinalDate() {
		return finalDate;
	}
	
	public Double getPrice() {
		return price;
	}
}
