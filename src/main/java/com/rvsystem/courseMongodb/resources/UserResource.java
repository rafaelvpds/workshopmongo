package com.rvsystem.courseMongodb.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rvsystem.courseMongodb.domain.User;
import com.rvsystem.courseMongodb.dto.UserDto;
import com.rvsystem.courseMongodb.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<UserDto>> findAll() {

		List<User> list = userService.findAll();
		List<UserDto> lisDto = list.stream().map(x -> new UserDto(x)).collect(Collectors.toList());

		return ResponseEntity.ok().body(lisDto);
	}

}
