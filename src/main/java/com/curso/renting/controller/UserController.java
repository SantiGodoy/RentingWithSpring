package com.curso.renting.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.curso.renting.dto.UserDto;
import com.curso.renting.exception.NotFoundException;
import com.curso.renting.mapper.MapperService;
import com.curso.renting.model.User;
import com.curso.renting.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired private MapperService<UserDto, User> userDtoToEntityMapper;
	@Autowired private MapperService<User, UserDto> userEntityToDtoMapper;
	@Autowired private UserService userService;
	
	@GetMapping
	public Page<UserDto> getAll(@RequestParam(value="page", defaultValue = "0", required = true) Integer page,
			@RequestParam(value="size", defaultValue = "10", required = true) Integer size) {
			Pageable pageRequest = PageRequest.of(page, size);
			// We return DTO objects to the client
			return userService.findAll(pageRequest).map(userEntityToDtoMapper::map); 		
	}
	
	@GetMapping("/{id}")
	public UserDto getOne(@PathVariable("id") Integer id) throws NotFoundException {
		return userService.findById(id)
				.map(userEntityToDtoMapper::map)
				.orElseThrow(() -> new NotFoundException(String.format("Cannot be found user with id:%s", id)));
	}
	
	@PostMapping
	public UserDto create(@RequestBody @Valid UserDto userDto){
		return userEntityToDtoMapper.map(userService.save(userDtoToEntityMapper.map(userDto)));
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable("id") Integer id, @RequestBody @Valid UserDto userDto)
			throws NotFoundException {
		User userFound = userService.findById(id)
							.orElseThrow(() -> new NotFoundException(
									String.format("Cannot be found user with id:%s", id)));
		User userToStore = new User(userFound.getId(), userDto.getDni(), userDto.getName(),
				userDto.getLastName(), userDto.getAddress(),userDto.getLocation(), userFound.getCars(), userFound.getRents());
		
		userService.save(userToStore);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) throws NotFoundException {
		User user = userService.findById(id)
			.orElseThrow(() -> new NotFoundException(
					String.format("Cannot be found user with id:%s", id)));
		userService.delete(user);
	}
}
