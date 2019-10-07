package com.curso.renting.controller;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

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

import com.curso.renting.dto.RentDto;
import com.curso.renting.exception.NotFoundException;
import com.curso.renting.exception.ValidationException;
import com.curso.renting.model.Car;
import com.curso.renting.model.Rent;
import com.curso.renting.model.User;
import com.curso.renting.service.CarService;
import com.curso.renting.service.RentService;
import com.curso.renting.service.UserService;
import com.curso.renting.service.mapper.MapperService;

@RestController
@RequestMapping("/rent")
public class RentController {
	@Autowired private MapperService<RentDto, Rent> rentDtoToEntityMapper;
	@Autowired private MapperService<Rent, RentDto> rentEntityToDtoMapper;
	@Autowired private RentService rentService;
	@Autowired private UserService userService;
	@Autowired private CarService carService;
	
	@GetMapping
	public Page<RentDto> getAll(@RequestParam(value="page", defaultValue = "0", required = true) Integer page,
			@RequestParam(value="size", defaultValue = "10", required = true) Integer size) {
			Pageable pageRequest = PageRequest.of(page, size);
			return rentService.findAll(pageRequest).map(rentEntityToDtoMapper::map); 		
	}
	
	@GetMapping("/{id}")
	public RentDto getOne(@PathVariable("id") Integer id) throws NotFoundException {
		return rentService.findById(id)
				.map(rentEntityToDtoMapper::map)
				.orElseThrow(() -> new NotFoundException(String.format("Cannot be found rent with id:%s", id)));
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable("id") Integer id, @RequestBody @Valid RentDto rentDto)
			throws NotFoundException, ParseException {
		Rent rentFound = rentService.findById(id)
							.orElseThrow(() -> new NotFoundException(
									String.format("Cannot be found rent with id:%s", id)));
		
		LocalDateTime initDate =
			    LocalDateTime.ofInstant(Instant.ofEpochSecond(rentDto.getInitDate()), ZoneId.systemDefault());
		LocalDateTime finalDate =
			    LocalDateTime.ofInstant(Instant.ofEpochSecond(rentDto.getFinalDate()), ZoneId.systemDefault());
		
		Rent rentToStore = new Rent(rentFound.getId(), rentFound.getUser(),rentFound.getCar(), 
				initDate, finalDate, rentDto.getPrice());
		
		rentService.save(rentToStore);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) throws NotFoundException {
		Rent rent = rentService.findById(id)
			.orElseThrow(() -> new NotFoundException(
					String.format("Cannot be found rent with id:%s", id)));
		rentService.delete(rent);
	}
	
	@PostMapping("car/{idCar}/user/{idUser}")
	public void rentCar(@PathVariable("idUser") Integer idUser, @PathVariable("idCar") Integer idCar, 
			@RequestParam(value = "days", required = true) Integer days) throws NotFoundException, ValidationException {
		Car car = carService.findById(idCar)
					.orElseThrow(() -> new NotFoundException(String.format("Cannot be found car with id:%s", idCar)));
		
		if(!car.getAvailability()) throw new ValidationException("The car is not available");
		
		User user = userService.findById(idUser)
				.orElseThrow(() -> new NotFoundException(String.format("Cannot be found user with id:%s", idUser)));
		
		RentDto rentDto = new RentDto(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()
				, LocalDateTime.now().plusDays(days).atZone(ZoneId.systemDefault()).toEpochSecond()
				, new Double(days*60), user, car);
		Rent rent = rentDtoToEntityMapper.map(rentDto);
		
		rentService.save(rent);
		car.getRents().add(rent);
		car.setAvailability(false);
		carService.save(car);
		user.getRents().add(rent);
		userService.save(user);
	}
}
