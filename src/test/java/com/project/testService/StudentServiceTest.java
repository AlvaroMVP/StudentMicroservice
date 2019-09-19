	package com.project.testService;

import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.reactivestreams.Publisher;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.model.Student;
import com.project.repository.StudentRepository;
import com.project.service.impl.StudentServiceImpl;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class StudentServiceTest {

	@Mock
	private StudentRepository studentRepository;
	
	@InjectMocks
	private StudentServiceImpl studentService;
	
	@Test
	public void findAll() {
		Student student = new Student();
		student.setFullName("");
		student.setGender("male");
		student.setTypeDocument("DNI");
		student.setNumberDocument("");
		
	 when(studentService.findAll()).thenReturn(Flux.just(student));
	 Flux<Student> actual = studentService.findAll();
	    assertResults(actual, student);
	}
	
    @Test
    public void findById_when_id_exist() {
        Student student2 = new Student();
        student2.setId("");
        student2.setFullName("");
        student2.setGender("male");
        student2.setBirthdate(new Date());
        student2.setTypeDocument("dni");
        student2.setNumberDocument("736723727");
        when(studentRepository.findById(student2.getId())).thenReturn(Mono.just(student2));
        Mono<Student> actual = studentService.findById(student2.getId());
        assertResults(actual, student2);
    }
    
    @Test
    public void findById_when_id_NO_exist() {
        Student student2 = new Student();
        student2.setId("");
        student2.setFullName("");
        student2.setGender("male");
        student2.setBirthdate(new Date());
        student2.setTypeDocument("dni");
        student2.setNumberDocument("736723727");
        when(studentRepository.findById(student2.getId())).thenReturn(Mono.empty());
        Mono<Student> actual = studentService.findById(student2.getId());
        assertResults(actual);
    }
    
    @Test
    public void save() {
      Student s = new Student();
      s.setId("10");
      s.setFullName("Victor");
      s.setGender("Masculino");
      s.setBirthdate(new Date());
      s.setTypeDocument("DNI");
      s.setNumberDocument("44443333");
      when(studentRepository.save(s)).thenReturn(Mono.just(s));
      Mono<Student> actual = studentService.save(s);
      assertResults(actual, s);
    }
    
    @Test
    public void delete() {
      Student stud = new Student();
      stud.setId("10");
      stud.setFullName("Victor");
      stud.setGender("Masculino");
      stud.setBirthdate(new Date());
      stud.setTypeDocument("DNI");
      stud.setNumberDocument("44443333");
      
      when(studentRepository.delete(stud)).thenReturn(Mono.empty());
    }
    
    @Test
    public void findBynNumberID() {
      Student s = new Student();
      s.setId("10");
      s.setFullName("Victor");
      s.setGender("Masculino");
      s.setBirthdate(new Date());
      s.setTypeDocument("DNI");
      s.setNumberDocument("44443333");
      final String numberDocument = "736723727";
      when(studentRepository.findBynumberDocument(numberDocument)).thenReturn(Mono.just(s));
      Mono<Student> actual = studentService.findBynumberDocument(numberDocument);
      assertResults(actual,s);
    }
    
    @Test
    public void findByName() {
      Student s = new Student();
      s.setId("10");
      s.setFullName("Victor");
      s.setGender("Masculino");
      s.setBirthdate(new Date());
      s.setTypeDocument("DNI");
      s.setNumberDocument("44443333");
      final String fullName = "Victor";
      when(studentRepository.findByFullName(fullName)).thenReturn(Mono.just(s));
      Mono<Student> actual = studentService.findByFullName(fullName);
      assertResults(actual,s);
    }
    
	private void assertResults(Publisher<Student> publisher, Student... expectedProducts) {
        StepVerifier
            .create(publisher)
            .expectNext(expectedProducts)
            .verifyComplete();
    }
	
	
	
	
	
}
