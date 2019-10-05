package com.curso.renting.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
	 * Deletes a car from the database
	 * @param car Car to delete
	 */
	void delete(Car car);
}
