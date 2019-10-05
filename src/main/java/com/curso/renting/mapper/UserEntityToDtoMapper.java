package com.curso.renting.mapper;

import org.modelmapper.ModelMapper;

import com.curso.renting.dto.UserDto;
import com.curso.renting.model.User;

public class UserEntityToDtoMapper implements MapperService<User, UserDto> {

	@Override
	public UserDto map(User user) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(user, UserDto.class);
	}
}
