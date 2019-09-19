package com.project.service.impl;

import com.project.model.Student;
import com.project.repository.StudentRepository;
import com.project.service.StudentInterface;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentInterface {

  @Autowired
  public StudentRepository studentRepository;

  @Override
  public Flux<Student> findAll() {
    return studentRepository.findAll();
  }

  @Override
  public Mono<Student> findByFullName(String fullName) {
    return studentRepository.findByFullName(fullName);
  }


  @Override
  public Mono<Student> findBynumberDocument(String numberDocument) {
    return studentRepository.findBynumberDocument(numberDocument);
  }

  @Override
  public Flux<Student> findByDate(String birthdate) {
    return null;
  }

  @Override
  public Mono<Student> findById(String id) {
    return studentRepository.findById(id);
  }

  @Override
  public Mono<Student> save(Student student) {
    return studentRepository.save(student);
  }

  @Override
  public Mono<Void> delete(String id) {
    return studentRepository.deleteById(id);
  }

  @Override
  public Flux<Student> findByBirthdateBetween(Date birthdate, Date birthdate1) {
    return studentRepository.findByBirthdateBetween(birthdate, birthdate1);
  }

}