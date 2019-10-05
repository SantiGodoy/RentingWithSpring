package com.curso.renting.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	private Integer id;
	@NotNull(message = "El dni no puede ser nulo")
	private String dni;
	@NotNull(message = "El nombre no puede ser nulo")
	private String name;
	@NotNull(message = "El apellido no puede ser nulo")
	private String lastName;
	@NotNull(message = "La dirección no puede ser nula")
	private String address;
	@NotNull(message = "La localidad no puede ser nula")
	private String location;
}
