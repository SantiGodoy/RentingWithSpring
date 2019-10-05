package com.curso.renting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.renting.model.Rent;

public interface RentRepository extends JpaRepository<Rent, Integer> {

}
