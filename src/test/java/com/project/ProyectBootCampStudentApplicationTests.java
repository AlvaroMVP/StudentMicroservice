package com.project;

import com.project.model.Student;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProyectBootCampStudentApplicationTests {

  @Autowired
  private WebTestClient client;

  @Value("${config.base.endpoint}")
  private String url;

  @Test
  public void findTest() {

    client.get()
        .uri(url)
        .accept(MediaType.APPLICATION_JSON_UTF8)
        .exchange()
        .expectStatus().isOk()
        .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBodyList(Student.class)
        .consumeWith(response -> {
          List<Student> student = response.getResponseBody();
            student.forEach(p -> { 
            System.out.println(p.getId());
          });
        Assertions.assertThat(student.size() > 0).isTrue(); });
  }

}
