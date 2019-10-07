package com.curso.renting.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.curso.renting.dto.ResultRentDto;
import com.curso.renting.model.Car;

public interface CarService {
	
	/**
	 * Stores the given car in the database
	 * @param car Car object to store in database
	 * @return If the car is stored successfully, it returns the car.
	 * Otherwise, it will throw an IllegalArgumentException
	 */
	Car save(final Car car);
	
	/**
	 * Finds all the cars stored in the database
	 * @param pageable Pageable object with the page specifications
	 * @return Page with all the cars found
	 */
	Page<Car> findAll(Pageable pageable);
	
	/**
	 * Finds a car by his id
	 * @param id Identifier to search in the database
	 * @return If a car has the same id, it returns the car.
	 * Otherwise, it will return an empty Optional.
	 */
	Optional<Car> findById(Integer id);
	
	/**
	 * Finds cars by his owner id
	 * @param idUser Car owner
	 * @param page Page specifications
	 * @return Page with the cars owned by the user
	 */
	Page<Car> findByUserId(Integer idUser, Pageable page);
	
	/**
	 * Deletes a car from the database
	 * @param car Car to delete
	 */
	void delete(Car car);
	
	/**
	 * Calculates the profit given by a single car between two dates
	 * @param car Car found by his id
	 * @param initTimestamp Initial date as timestamp
	 * @param finalTimestamp Final date as timestamp
	 * @return ResultRentDto with the car title and his profit
	 */
	ResultRentDto carProfit(Car car, Long initTimestamp, Long finalTimestamp);
}
