package com.curso.renting.dto;

import javax.validation.constraints.NotNull;

import com.curso.renting.model.Car;
import com.curso.renting.model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentDto {
	@NotNull(message = "Initial date cannot be null")
	private Long initDate;
	@NotNull(message = "Final date cannot be null")
	private Long finalDate;
	@NotNull(message = "Price cannot be null")
	private Double price;
	@NotNull(message = "User cannot be null")
	private User user;
	@NotNull(message = "Car cannot be null")
	private Car car;
}
