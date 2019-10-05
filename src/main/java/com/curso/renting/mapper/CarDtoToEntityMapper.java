package com.curso.renting.mapper;

import org.modelmapper.ModelMapper;

import com.curso.renting.dto.CarDto;
import com.curso.renting.model.Car;

public class CarDtoToEntityMapper implements MapperService<CarDto, Car> {

	@Override
	public Car map(CarDto car) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(car, Car.class);
	}
}
