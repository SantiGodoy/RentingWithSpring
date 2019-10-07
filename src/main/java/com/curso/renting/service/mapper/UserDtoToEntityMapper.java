package com.curso.renting.service.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.curso.renting.dto.UserDto;
import com.curso.renting.model.User;

@Service
public class UserDtoToEntityMapper implements MapperService<UserDto, User> {

	@Override
	public User map(UserDto user) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(user, User.class);
	}
}
