package com.curso.renting.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true, length = 9, nullable = false)
	private String dni;
	@Column(nullable = false)
	private String name;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String address;
	@Column(nullable = false)
	private String location;
}
