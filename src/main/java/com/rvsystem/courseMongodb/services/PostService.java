package com.rvsystem.courseMongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rvsystem.courseMongodb.domain.Post;
import com.rvsystem.courseMongodb.repository.PostRepository;
import com.rvsystem.courseMongodb.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;

	public Post findById(String id) {
		Optional<Post> obj = postRepository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException("Id not found " + id));
	}

	public List<Post> findByTitle(String text) {
		return postRepository.findByTitleContainingIgnoreCase(text);
	}

}
