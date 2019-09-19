package com.project.testControlller;

import com.project.model.Student;

import com.project.service.impl.StudentServiceImpl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

/* Student test controller*/
@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ProyectBootCampStudentApplicationTests {

  @Autowired private WebTestClient client;
  
  @Autowired private StudentServiceImpl studentService;

  @Test
  public void create() {
    Student student = new Student("432dsffa22", "Andrea", "female", new Date(), "DNI", "43434343");

    client
        .post()
        .uri("/api/v1.0/")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .body(Mono.just(student), Student.class)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectHeader()
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBodyList(Student.class);
  }

  @Test
  public void findAll() {

    client
        .get()
        .uri("/api/v1.0/student")
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBodyList(Student.class)
        .consumeWith(
          response -> {
            List<Student> student = response.getResponseBody();
            student.forEach(p -> {
              System.out.println(p.getFullName());
            });
            Assertions.assertThat(student.size() > 0).isTrue();
          });
  }
  
  @Test
  public void findById() {

    Student student = studentService.findById("5d7c0696e7ac942af83bef07").block();
    client
        .get()
        .uri("/api/v1.0/student" + "/{id}", Collections.singletonMap("id", student.getId()))
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()
        .expectStatus()
        .isOk()
        .expectHeader()
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBody(Student.class)
        .consumeWith(
            response -> {
              Student p = response.getResponseBody();
              Assertions.assertThat(p.getId()).isNotEmpty();
              Assertions.assertThat(p.getId().length() > 0).isTrue();
            });
  }
  
  @Test
  public void save() {

    Student student = studentService.findByFullName("Juan Perez").block();

    Student studentEdit =
              new Student(
                      "5d81247b19e5a726d811c93e",
                      "Juan Perez",
                      "male",
                      new Date(),
                      "DNI",
                      "84848444"
                      );

    client
              .put()
              .uri("/api/v1.0" + "/{id}", Collections.singletonMap("id", student.getId()))
              .contentType(MediaType.APPLICATION_JSON_UTF8)
              .accept(MediaType.APPLICATION_JSON_UTF8)
              .body(Mono.just(studentEdit), Student.class)
              .exchange()
              .expectStatus()
              .isCreated()
              .expectHeader()
              .contentType(MediaType.APPLICATION_JSON_UTF8)
              .expectBody()
              .jsonPath("$.id")
              .isNotEmpty()
              .jsonPath("$.numberDocument")
              .isEqualTo(84848444);
  }
  
  @Test
  public void findByDocument() {

    Student student = studentService.findBynumberDocument("84848444").block();
    client
              .get()
              .uri(
                      "/api/v1.0" + "/numberDocument/{numberDocument}",
                      Collections.singletonMap("numberDocument", student.getNumberDocument()))
              .accept(MediaType.APPLICATION_JSON_UTF8)
              .exchange()
              .expectStatus()
              .isOk()
              .expectHeader()
              .contentType(MediaType.APPLICATION_JSON_UTF8)
              .expectBody(Student.class)
              .consumeWith(
                response -> {
                  Student s = response.getResponseBody();
                  Assertions.assertThat(s.getNumberDocument()).isNotEmpty();
                  Assertions.assertThat(s.getNumberDocument().length() > 0).isTrue();
                });
  }
  
  @Test
  public void findByFullName() {

    Student parent = studentService.findByFullName("Micaela Rubin").block();
    client
              .get()
              .uri("/api/v1.0" + "/fullName/{fullName}", 
              Collections.singletonMap("fullName", parent.getFullName()))
              .accept(MediaType.APPLICATION_JSON_UTF8)
              .exchange()
              .expectStatus()
              .isOk()
              .expectHeader()
              .contentType(MediaType.APPLICATION_JSON_UTF8)
              .expectBody(Student.class)
              .consumeWith(
                response -> {
                  Student p = response.getResponseBody();
                  Assertions.assertThat(p.getFullName()).isNotEmpty();
                  Assertions.assertThat(p.getFullName().length() > 0).isTrue();
                });
  }
  
 
  
  @Test
  public void delete() {

    Student parent = studentService.findById("5d832c7aad119347a070e241").block();
    client
              .delete()
              .uri("/api/v1.0" + "/{id}", Collections.singletonMap("id", parent.getId()))
              .exchange()
              .expectStatus()
              .isNoContent()
              .expectBody()
              .isEmpty();

    client
              .get()
              .uri("/api/v1.0" + "/{id}", Collections.singletonMap("id", parent.getId()))
              .exchange()
              .expectStatus()
              .isNotFound()
              .expectBody()
              .isEmpty();
  }

  
  
  
}
