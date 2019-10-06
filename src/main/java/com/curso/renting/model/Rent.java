package com.curso.renting.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_cars")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Rent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@NotNull
	@JsonManagedReference
	@ManyToOne(fetch = FetchType.LAZY)
	private Car car;
	
	@NotNull
	@Column(name = "initial_date", columnDefinition="DATETIME")
	private LocalDateTime initDate;
	
	@NotNull
	@Column(name = "final_date", columnDefinition="DATETIME")
	private LocalDateTime finalDate;
	
	@NotNull
	private Double price;
}

//LocalDate.from(new Date().toInstance())
//LocalDate.ofEpochDay(new Date().toInstance().getEpochSecond())