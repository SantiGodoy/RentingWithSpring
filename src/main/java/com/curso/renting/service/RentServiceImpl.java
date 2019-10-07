package com.curso.renting.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.curso.renting.model.Rent;
import com.curso.renting.repository.RentRepository;

@Service
public class RentServiceImpl implements RentService {
	@Autowired private RentRepository rentRepository;

	@Override
	public Rent save(Rent rent) {
		if(rent == null) throw new IllegalArgumentException();
		return rentRepository.save(rent);
	}

	@Override
	public Page<Rent> findAll(Pageable pageable) {
		return rentRepository.findAll(pageable);
	}

	@Override
	public Optional<Rent> findById(Integer id) {
		return rentRepository.findById(id);
	}
	
	@Override
	public void delete(Rent rent) {
		if(rent == null) throw new IllegalArgumentException();
		rentRepository.delete(rent);
	}

	@Override
	public Page<Rent> findByUserId(Integer idUser, Pageable pageable) {
		return rentRepository.findByUserId(idUser, pageable);
	}

	@Override
	public Page<Rent> findByCarId(Integer idCar, LocalDateTime initDate, LocalDateTime finalDate,
			Pageable pageable) {
		return rentRepository.findByCarIdAndInitDateGreaterThanEqualAndFinalDateLessThanEqual(idCar, 
				initDate, finalDate, pageable);
	}
}
