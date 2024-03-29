package com.curso.renting.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.curso.renting.model.Rent;

public interface RentService {
	
	/**
	 * Stores the given rent in the database
	 * @param rent Rent object to store in database
	 * @return If the rent is stored successfully, it returns the rent.
	 * Otherwise, it will throw an IllegalArgumentException
	 */
	Rent save(final Rent rent);
	
	/**
	 * Finds all the rents stored in the database
	 * @param pageable Pageable object with the page specifications
	 * @return Page with all the rents found
	 */
	Page<Rent> findAll(Pageable pageable);
	
	/**
	 * Finds a rent by his id
	 * @param id Identifier to search in the database
	 * @return If a rent has the same id, it returns the rent.
	 * Otherwise, it will return an empty Optional.
	 */
	Optional<Rent> findById(Integer id);
	
	/**
	 * Finds rents by his user id
	 * @param idUser User who has rented cars
	 * @param pageable Page specifications
	 * @return Page with all the user rents
	 */
	Page<Rent> findByUserId(Integer idUser, Pageable pageable);
	
	/**
	 * Finds car rents between two dates by his car id 
	 * @param idCar Identifier to find the car
	 * @param initDate Initial LocalDateTime
	 * @param finalDate Final LocalDateTime
	 * @param pageable Page specifications
	 * @return Page with all the car rents between the two dates
	 */
	Page<Rent> findByCarId(Integer idCar, 
			LocalDateTime initDate, LocalDateTime finalDate, Pageable pageable);
	
	/**
	 * Deletes a rent from the database
	 * @param rent Rent to delete
	 */
	void delete(Rent rent);
}
