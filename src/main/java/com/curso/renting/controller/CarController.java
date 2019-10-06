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

import com.curso.renting.dto.CarDto;
import com.curso.renting.exception.NotFoundException;
import com.curso.renting.mapper.MapperService;
import com.curso.renting.model.Car;
import com.curso.renting.service.CarService;

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired private MapperService<CarDto, Car> carDtoToEntityMapper;
	@Autowired private MapperService<Car, CarDto> carEntityToDtoMapper;
	@Autowired private CarService carService;
	
	@GetMapping
	public Page<CarDto> getAll(@RequestParam(value="page", defaultValue = "0", required = true) Integer page,
			@RequestParam(value="size", defaultValue = "10", required = true) Integer size) {
			Pageable pageRequest = PageRequest.of(page, size);
			return carService.findAll(pageRequest).map(carEntityToDtoMapper::map); 		
	}
	
	@GetMapping("/{id}")
	public CarDto getOne(@PathVariable("id") Integer id) throws NotFoundException {
		return carService.findById(id)
				.map(carEntityToDtoMapper::map)
				.orElseThrow(() -> new NotFoundException(String.format("Cannot be found car with id:%s", id)));
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
				carDto.getBrand(), carFound.getRents(), carFound.getUser());
		
		carService.save(carToStore);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) throws NotFoundException {
		Car car = carService.findById(id)
			.orElseThrow(() -> new NotFoundException(
					String.format("Cannot be found car with id:%s", id)));
		carService.delete(car);
	}
}
