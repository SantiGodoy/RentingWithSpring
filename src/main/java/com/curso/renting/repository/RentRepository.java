package com.curso.renting.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.renting.model.Rent;

public interface RentRepository extends JpaRepository<Rent, Integer> {
	Page<Rent> findByUserId(Integer idUser, Pageable page);
	Page<Rent> findByCarIdAndInitDateGreaterThanEqualAndFinalDateLessThanEqual(Integer idCar, 
			LocalDateTime initDate, LocalDateTime finalDate, Pageable pageable);
}
