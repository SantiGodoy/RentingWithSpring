package com.curso.renting.dto;

import javax.validation.constraints.NotNull;

import com.curso.renting.model.Car;
import com.curso.renting.model.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RentDto {
	private Integer id;
	private Long initDate;
	private Long finalDate;
	@NotNull(message = "El precio no puede ser nulo")
	private Double price;
	private User user;
	private Car car;
}
