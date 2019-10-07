package com.curso.renting.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultRentDto {
	private String brand;
	private String model;
	private LocalDateTime initDate;
	private LocalDateTime finalDate;
	private Double price;
	
	public String toString() {
		return brand + " " + model + " " + "[" + initDate + "/" + finalDate + "]"
				+ "\nProfit: " + price + "€";
	}
}
