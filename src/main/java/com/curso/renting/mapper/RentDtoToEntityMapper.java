package com.curso.renting.mapper;

import org.modelmapper.ModelMapper;

import com.curso.renting.dto.RentDto;
import com.curso.renting.model.Rent;

public class RentDtoToEntityMapper implements MapperService<RentDto, Rent>{

	@Override
	public Rent map(RentDto rent) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(rent, Rent.class);
	}
}
