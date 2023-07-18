package com.rvsystem.courseMongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rvsystem.courseMongodb.domain.User;
import com.rvsystem.courseMongodb.dto.UserDto;
import com.rvsystem.courseMongodb.repository.UserRepository;
import com.rvsystem.courseMongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {

		return userRepository.findAll();

	}

	public User findById(String id) {
		Optional<User> obj = userRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("Id not found " + id));
	}

	public User insert(User obj) {
		return userRepository.insert(obj);
	}

	public User fromDto(UserDto objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
