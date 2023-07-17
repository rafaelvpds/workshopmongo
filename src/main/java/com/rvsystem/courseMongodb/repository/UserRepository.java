package com.rvsystem.courseMongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rvsystem.courseMongodb.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
