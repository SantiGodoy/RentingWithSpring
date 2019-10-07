package com.curso.renting.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.curso.renting.exception.NotFoundException;
import com.curso.renting.model.User;

public interface UserService {
	
	/**
	 * Stores the given user in the database
	 * @param user User object to store in database
	 * @return If the user is stored successfully, it returns the user.
	 * Otherwise, it will throw an IllegalArgumentException
	 */
	User save(final User user);
	
	/**
	 * Finds all the users stored in the database
	 * @param pageable Pageable object with the page specifications
	 * @return Page with all the users found
	 */
	Page<User> findAll(Pageable pageable);
	
	/**
	 * Finds an user by his id
	 * @param id Identifier to search in the database
	 * @return If an user has the same id, it returns the user.
	 * Otherwise, it will return an empty Optional.
	 */
	Optional<User> findById(Integer id);
	
	/**
	 * Deletes an user from the database
	 * @param user User to delete
	 */
	void delete(User user);
	
	/**
	 * Assigns the given user as the owner of the car
	 * @param user New car owner
	 * @param idCar Identifier to find the car
	 * @throws NotFoundException If the car does not exist
	 */
	void userToCar(User user, Integer idCar) throws NotFoundException;
}
