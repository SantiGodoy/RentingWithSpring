package com.curso.renting.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.curso.renting.model.User;
import com.curso.renting.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired private UserRepository userRepository;
	
	@Override
	public User save(User user) {
		if(user == null) throw new IllegalArgumentException();
		return userRepository.save(user);
	}

	@Override
	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	public Optional<User> findById(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public void delete(User user) {
		if(user == null) throw new IllegalArgumentException();
		userRepository.delete(user);
	}
}
