package com.project.repository;

import com.project.model.Student;

import java.util.Date;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentRepository extends ReactiveMongoRepository<Student, String> {
	
  Flux<Student> findByFullName(String fullName);
  
  Mono<Student> findBynumberDocument(String numberDocument);
  
  Flux<Student> findByBirthdateBetween(Date birthdate,Date birthdate1);
}