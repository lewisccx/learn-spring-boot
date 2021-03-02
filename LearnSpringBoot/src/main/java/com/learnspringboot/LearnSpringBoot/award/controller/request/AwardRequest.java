package com.learnspringboot.LearnSpringBoot.award.controller.request;

import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotNull;
@Data
public class AwardRequest {

    @NotNull(message = "please provide a studentId")
    @NumberFormat
    private Integer studentId;

    @NotNull(message = "please provide a awardId")
    @NumberFormat
    private Integer awardId;

}
