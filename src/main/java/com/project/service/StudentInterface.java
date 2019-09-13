package com.project.service;

import com.project.model.Student;
import java.util.Date;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentInterface {
  void create(Student s);

  public Flux<Student> findAll();
  
  public Flux<Student> findByFullName(String fullName);
  
  public Flux<Student> findByDate(String birthdate);
  
  public Flux<Student> findByBirthdateBetween(Date birthdate, Date birthdate1);
   
  public Mono<Student> findBynumberDocument(String numberDocument);
   
  public Mono<Student> findById(String id);
  
  public Mono<Student> save(Student student);
  
  public Mono<Void> delete(String id);



}