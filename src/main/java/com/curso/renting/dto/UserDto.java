package com.curso.renting.dto;

import javax.validation.constraints.NotNull;

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
	
	public UserDto() {}
	
	public UserDto(Integer id, String dni, String name,
			String lastName, String address, String location) {
		this.id = id;
		this.dni = dni;
		this.name = name;
		this.lastName = lastName;
		this.address = address;
		this.location = location;
	}

	public Integer getId() {
		return id;
	}
	
	public String getDni() {
		return dni;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getLocation() {
		return location;
	}
}
