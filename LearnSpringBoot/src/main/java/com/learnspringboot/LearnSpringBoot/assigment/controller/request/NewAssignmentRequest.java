package com.learnspringboot.LearnSpringBoot.assigment.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewAssignmentRequest {

    @NotEmpty(message = "Please provide a email address")
    private String name;

    @NotNull(message = "Please provide a grade")
    @Min(0)
    @Max(100)
    private int grade;


}
