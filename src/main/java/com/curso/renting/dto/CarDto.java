package com.curso.renting.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarDto {
	@NotNull(message = "Number plate cannot be null")
	private String numberPlate;
	@NotNull(message = "Model cannot be null")
	private String model;
	@NotNull(message = "Brand cannot be null")
	private String brand;
	@NotNull(message = "Availability cannot be null")
	private Boolean availability;
}
