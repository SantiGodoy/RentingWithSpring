package com.curso.renting.dto;

import javax.validation.constraints.NotNull;

import com.curso.renting.model.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarDto {
	private Integer id;
	@NotNull(message = "La matrícula no puede ser nula")
	private String numberPlate;
	@NotNull(message = "El modelo no puede ser nulo")
	private String model;
	@NotNull(message = "La marca no puede ser nula")
	private String brand;
	@NotNull(message = "Tiene que tener un dueño")
	private User user;
}
