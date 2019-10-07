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
import com.curso.renting.exception.NotFoundException;
import com.curso.renting.exception.ValidationException;
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
	public ResultRentDto carProfit(Integer id, Long initTimestamp, Long finalTimestamp) throws NotFoundException, ValidationException {
		if(finalTimestamp < initTimestamp) throw new ValidationException();
		
		Car carFound = carRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(
						String.format("Cannot be found car with id:%s", id)));
		LocalDateTime initDate =
			    LocalDateTime.ofInstant(Instant.ofEpochSecond(initTimestamp), ZoneId.systemDefault());
		LocalDateTime finalDate =
			    LocalDateTime.ofInstant(Instant.ofEpochSecond(finalTimestamp), ZoneId.systemDefault());
		
		Double carProfit = 0.0;
		
		for(Rent rent : carFound.getRents())
			carProfit += (initDate.isBefore(rent.getInitDate()) && finalDate.isAfter(initDate)) 
					? rent.getPrice() : 0;
					
		return new ResultRentDto(carFound.getBrand() + " " + carFound.getModel(), initDate, finalDate, carProfit);
	}
}
