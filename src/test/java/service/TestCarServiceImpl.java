package service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.curso.renting.exception.NotFoundException;
import com.curso.renting.exception.ValidationException;
import com.curso.renting.model.Car;
import com.curso.renting.model.Rent;
import com.curso.renting.model.User;
import com.curso.renting.repository.CarRepository;
import com.curso.renting.service.CarServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TestCarServiceImpl {
	@InjectMocks private CarServiceImpl carService;
	@Mock private CarRepository carRepository;

	@Test(expected = ValidationException.class)
	public void whenTheInitialDateIsGreaterThanFinalDateShouldThrowValidationException() 
			throws ValidationException, NotFoundException {
		final Integer idCar = 1;
		final Long initDate = 44348L;
		final Long finalDate = 40000L;
		
		carService.carProfit(idCar, initDate, finalDate);
	}
	
	@Test(expected = NotFoundException.class)
	public void whenTheCarDoesNotExistsShouldThrowNotFoundException() 
			throws ValidationException, NotFoundException {
		final Integer idCar = 1;
		final Long initDate = 40000L;
		final Long finalDate = 43500L;
		
		carService.carProfit(idCar, initDate, finalDate);
	}
	
	@Test
	public void WhenCarExistsShouldReturnCorrectProfitValue() throws NotFoundException, ValidationException {
		final Integer idCar = 1;
		final String numberPlate = "4562AAB";
		final String model = "Ibiza";
		final String brand = "SEAT";
		final Long initTimestamp = 40000L;
		final Long finalTimestamp = 40003500L;
		final Boolean availability = true;
		
		LocalDateTime initDate =
			    LocalDateTime.ofInstant(Instant.ofEpochSecond(initTimestamp), ZoneId.systemDefault());
		LocalDateTime finalDate =
			    LocalDateTime.ofInstant(Instant.ofEpochSecond(finalTimestamp), ZoneId.systemDefault());
		
		final List<Rent> rents = new ArrayList<Rent>();
		rents.add(new Rent(idCar, new User(), new Car(), initDate, 
				finalDate, new Double(50)));
		final User user = new User();
		
		Car car = new Car(idCar, numberPlate, model, brand, availability, rents, user);
		
		Mockito.when(carRepository.findById(idCar))
			.thenReturn(Optional.ofNullable(car));
		
		Assert.assertEquals(50.0d, carService.carProfit(idCar, 39000L, 45000L).getPrice(), 0.0001);
	}
}
