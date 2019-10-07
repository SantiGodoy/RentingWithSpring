package com.curso.renting.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	@NotNull(message = "DNI cannot be null")
	private String dni;
	@NotNull(message = "Name cannot be null")
	private String name;
	@NotNull(message = "Last name cannot be null")
	private String lastName;
	@NotNull(message = "Address cannot be null")
	private String address;
	@NotNull(message = "Location cannot be null")
	private String location;
}
