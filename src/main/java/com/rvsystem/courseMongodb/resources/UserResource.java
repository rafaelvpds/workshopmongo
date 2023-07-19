package com.rvsystem.courseMongodb.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rvsystem.courseMongodb.domain.Post;
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

	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDto> findById(@PathVariable String id) {
		User obj = userService.findById(id);
		return ResponseEntity.ok().body(new UserDto(obj));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody UserDto objeDto) {
		User obj = userService.fromDto(objeDto);
		obj = userService.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		userService.delete(id);

		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable String id, @RequestBody UserDto objDto) {
		User obj = userService.fromDto(objDto);
		obj.setId(id);
		obj = userService.update(obj);

		return ResponseEntity.noContent().build();

	}

	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
		User obj = userService.findById(id);
		return ResponseEntity.ok().body(obj.getPosts());
	}

}
