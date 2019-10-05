package com.curso.renting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.renting.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
