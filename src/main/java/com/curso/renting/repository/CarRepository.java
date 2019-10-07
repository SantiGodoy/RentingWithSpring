package com.curso.renting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.renting.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {
	Page<Car> findByUserId(Integer idUser, Pageable page);
}
