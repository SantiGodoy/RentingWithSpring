package com.curso.renting.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RentDto {
	private Integer id;
	private Integer initDate;
	private Integer finalDate;
	@NotNull(message = "El precio no puede ser nulo")
	private Double price;
}
