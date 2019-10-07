package com.curso.renting.service.mapper;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.curso.renting.dto.RentDto;
import com.curso.renting.model.Rent;

@Service
public class RentEntityToDtoMapper implements MapperService<Rent, RentDto>{

	@Override
	public RentDto map(Rent rent) {
		ModelMapper modelMapper = new ModelMapper();
		
		Converter<LocalDateTime, Long> toLong = new AbstractConverter<LocalDateTime, Long>() {
	        @Override
	        protected Long convert(LocalDateTime date) {
	    		return date.atZone(ZoneId.systemDefault()).toEpochSecond();
	        }
	    };
	    
	    modelMapper.addConverter(toLong);
		
		return modelMapper.map(rent, RentDto.class);
	}
}
