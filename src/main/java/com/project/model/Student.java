package com.project.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.io.Serializable;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "student")

//Employee POJO class to represent entity Student
public class Student implements Serializable {
  public static final long serialVersionUID = 1L;
  //public field
  @Id
  @NotNull
  public String id;
  //default fields
  @NotEmpty(message = "name cannot be empty")
  String fullName;
  @NotEmpty(message = "gender cannot be empty")
  public String gender;
  @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
  public Date birthdate;
  @NotEmpty(message = "Document type cannot be empty")
  public String typeDocument;
  @NotEmpty(message = "number document cannot be empty")
  @Size(min = 8, max = 8, message = "document number can only have 8 characters")
  public String numberDocument;
 
}