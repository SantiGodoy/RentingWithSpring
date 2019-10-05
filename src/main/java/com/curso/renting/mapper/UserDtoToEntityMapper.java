package com.curso.renting.mapper;

import org.modelmapper.ModelMapper;

import com.curso.renting.dto.UserDto;
import com.curso.renting.model.User;

public class UserDtoToEntityMapper implements MapperService<UserDto, User> {

	@Override
	public User map(UserDto user) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(user, User.class);
	}
}
