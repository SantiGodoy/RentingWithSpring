package com.curso.renting.mapper;

import org.modelmapper.ModelMapper;

import com.curso.renting.dto.RentDto;
import com.curso.renting.model.Rent;

public class RentEntityToDtoMapper implements MapperService<Rent, RentDto>{

	@Override
	public RentDto map(Rent rent) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(rent, RentDto.class);
	}
}
