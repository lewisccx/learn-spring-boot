package com.learnspringboot.LearnSpringBoot.contact.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewContactRequest {

    /*
     * For Integer validation
     * */
    @NotNull(message = "Please provide a mobile phone number")
    private Integer mobile;

    /*
     * For String validation
     * */
    @NotEmpty(message = "Please provide a email address")
    @Email(message = "Email address provided is not valid")
    private String email;

}
