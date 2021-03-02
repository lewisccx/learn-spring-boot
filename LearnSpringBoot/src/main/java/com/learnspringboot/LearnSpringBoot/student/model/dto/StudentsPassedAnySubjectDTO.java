package com.learnspringboot.LearnSpringBoot.student.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface StudentsPassedAnySubjectDTO {

     String getFirstName();

     String getLastName();

     String getSubjectName();

     String getContact();
}
