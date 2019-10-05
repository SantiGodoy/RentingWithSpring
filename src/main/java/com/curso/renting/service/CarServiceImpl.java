package com.curso.renting.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.curso.renting.model.Car;
import com.curso.renting.repository.CarRepository;

@Service
public class CarServiceImpl implements CarService {
	@Autowired private CarRepository carRepository;
	
	@Override
	public Car save(Car car) {
		if(car == null) throw new IllegalArgumentException();
		return carRepository.save(car);
	}

	@Override
	public Page<Car> findAll(Pageable pageable) {
		return carRepository.findAll(pageable);
	}

	@Override
	public Optional<Car> findById(Integer id) {
		return carRepository.findById(id);
	}

	@Override
	public void delete(Car car) {
		if(car == null) throw new IllegalArgumentException();
		carRepository.delete(car);
	}
}
