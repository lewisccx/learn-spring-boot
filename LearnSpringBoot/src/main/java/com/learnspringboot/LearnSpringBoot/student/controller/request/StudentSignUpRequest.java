package com.learnspringboot.LearnSpringBoot.student.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentSignUpRequest {

    @NotEmpty(message = "Please provide a first name")
    private String firstName;


    @NotEmpty(message = "Please provide a last name")
    private String lastName;
}
