package com.curso.renting.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@Size(max = 9)
	@Column(unique = true)
	private String dni;
	
	@NotNull
	private String name;
	
	@NotNull
	@Column(name = "last_name")
	private String lastName;
	
	@NotNull
	private String address;
	
	@NotNull
	private String location;
	
	@JsonBackReference
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Car> cars;
	@JsonBackReference
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<Rent> rents;
}
