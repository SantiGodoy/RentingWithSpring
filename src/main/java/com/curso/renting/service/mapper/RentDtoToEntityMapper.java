package com.curso.renting.service.mapper;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.curso.renting.dto.RentDto;
import com.curso.renting.model.Rent;

@Service
public class RentDtoToEntityMapper implements MapperService<RentDto, Rent>{

	@Override
	public Rent map(RentDto rent) {
		ModelMapper modelMapper = new ModelMapper();
		
		Converter<Long, LocalDateTime> toLocalDatetime = new AbstractConverter<Long, LocalDateTime>() {
	        @Override
	        protected LocalDateTime convert(Long timestamp) {
	    		return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), ZoneId.systemDefault());
	        }
	    };
	    
	    modelMapper.addConverter(toLocalDatetime);
		return modelMapper.map(rent, Rent.class);
	}
}
