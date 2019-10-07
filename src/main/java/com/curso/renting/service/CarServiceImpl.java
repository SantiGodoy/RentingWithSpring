package com.curso.renting.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.curso.renting.dto.ResultRentDto;
import com.curso.renting.model.Car;
import com.curso.renting.model.Rent;
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

	@Override
	public Page<Car> findByUserId(Integer idUser, Pageable page) {
		return carRepository.findByUserId(idUser, page);
	}

	@Override
	public ResultRentDto carProfit(Car car, Long initTimestamp, Long finalTimestamp) {
		
		LocalDateTime initDate =
			    LocalDateTime.ofInstant(Instant.ofEpochSecond(initTimestamp), ZoneId.systemDefault());
		LocalDateTime finalDate =
			    LocalDateTime.ofInstant(Instant.ofEpochSecond(finalTimestamp), ZoneId.systemDefault());
		
		Double carProfit = 0.0;
		
		for(Rent rent : car.getRents())
			carProfit += (initDate.isBefore(rent.getInitDate())) 
					? rent.getPrice() : 0;
					
		return new ResultRentDto(car.getBrand(), car.getModel(), initDate, finalDate, carProfit);
	}
}
