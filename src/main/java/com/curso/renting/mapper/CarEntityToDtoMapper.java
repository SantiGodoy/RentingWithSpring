package com.curso.renting.mapper;

import org.modelmapper.ModelMapper;

import com.curso.renting.dto.CarDto;
import com.curso.renting.model.Car;

public class CarEntityToDtoMapper implements MapperService<Car, CarDto> {

	@Override
	public CarDto map(Car car) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(car, CarDto.class);
	}
}
