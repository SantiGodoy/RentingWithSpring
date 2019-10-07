package com.curso.renting.service.mapper;

public interface MapperService <T, R> {
	/**
	 * Maps a T object into another R
	 * @param t T object to be mapped
	 * @return R object already mapped from T	
	 */
	public R map(T t);
}
