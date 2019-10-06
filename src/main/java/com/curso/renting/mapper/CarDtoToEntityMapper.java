package com.curso.renting.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.curso.renting.dto.CarDto;
import com.curso.renting.model.Car;

@Service
public class CarDtoToEntityMapper implements MapperService<CarDto, Car> {

	@Override
	public Car map(CarDto car) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(car, Car.class);
	}
}
