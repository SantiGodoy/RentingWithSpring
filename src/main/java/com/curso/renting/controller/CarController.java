package com.curso.renting.controller;

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

import com.curso.renting.dto.CarDto;
import com.curso.renting.dto.RentDto;
import com.curso.renting.dto.ResultRentDto;
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
@RequestMapping("/car")
public class CarController {
	@Autowired private MapperService<CarDto, Car> carDtoToEntityMapper;
	@Autowired private MapperService<Car, CarDto> carEntityToDtoMapper;
	@Autowired private MapperService<RentDto, Rent> rentDtoToEntityMapper;
	@Autowired private CarService carService;
	@Autowired private UserService userService;
	@Autowired private RentService rentService;
	
	@GetMapping
	public Page<CarDto> getAll(@RequestParam(value="page", defaultValue = "0", required = false) Integer page,
			@RequestParam(value="size", defaultValue = "10", required = false) Integer size) {
			Pageable pageRequest = PageRequest.of(page, size);
			return carService.findAll(pageRequest).map(carEntityToDtoMapper::map); 		
	}
	
	@GetMapping("/{id}")
	public CarDto getOne(@PathVariable("id") Integer id) throws NotFoundException {
		return carService.findById(id)
				.map(carEntityToDtoMapper::map)
				.orElseThrow(() -> new NotFoundException(String.format("Cannot be found car with id:%s", id)));
	}
	
	@GetMapping("/user/{idUser}")
	public Page<CarDto> getUserCars(@PathVariable("idUser") Integer idUser,@RequestParam(value="page", 
		defaultValue = "0", required = false) Integer page, @RequestParam(value="size", 
		defaultValue = "10", required = false) Integer size) throws NotFoundException {
		Pageable pageRequest = PageRequest.of(page, size); 
		
		return carService.findByUserId(idUser, pageRequest)
				.map(carEntityToDtoMapper::map);
	}
	
	
	@PostMapping("/{idCar}/rent/{idUser}")
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
	
	@PostMapping
	public CarDto create(@RequestBody @Valid CarDto carDto){
		return carEntityToDtoMapper.map(carService.save(carDtoToEntityMapper.map(carDto)));
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable("id") Integer id, @RequestBody @Valid CarDto carDto)
			throws NotFoundException {
		Car carFound = carService.findById(id)
							.orElseThrow(() -> new NotFoundException(
									String.format("Cannot be found car with id:%s", id)));
		Car carToStore = new Car(carFound.getId(), carDto.getNumberPlate(), carDto.getModel(),
				carDto.getBrand(), carDto.getAvailability(), carFound.getRents(), carFound.getUser());
		
		carService.save(carToStore);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) throws NotFoundException {
		Car car = carService.findById(id)
			.orElseThrow(() -> new NotFoundException(
					String.format("Cannot be found car with id:%s", id)));
		carService.delete(car);
	}
	
	@GetMapping("/{id}/{initDate}/{finalDate}")
	public ResultRentDto carProfit(@PathVariable("id") Integer id, @PathVariable("initDate") Long initTimestamp,
			@PathVariable("finalDate") Long finalTimestamp) throws NotFoundException, ValidationException {
		
		if(finalTimestamp < initTimestamp) throw new ValidationException();
		
		Car carFound = carService.findById(id)
				.orElseThrow(() -> new NotFoundException(
						String.format("Cannot be found car with id:%s", id)));
		return carService.carProfit(carFound, initTimestamp, finalTimestamp);
	}
}
