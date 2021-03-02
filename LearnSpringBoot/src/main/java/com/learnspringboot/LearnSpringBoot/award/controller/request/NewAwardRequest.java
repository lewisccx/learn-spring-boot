package com.learnspringboot.LearnSpringBoot.award.controller.request;

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
public class NewAwardRequest {

    @NotEmpty(message = "please provide a name for the award")
    private String name;

    @NotNull(message = "Please provide a mobile phone number")
    @Min(1000)
    @Max(5000)
    private Integer amount;
}
