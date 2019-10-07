package com.curso.renting.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.curso.renting.dto.CarDto;
import com.curso.renting.model.Car;

@Service
public class CarEntityToDtoMapper implements MapperService<Car, CarDto> {

	@Override
	public CarDto map(Car car) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(car, CarDto.class);
	}
}
