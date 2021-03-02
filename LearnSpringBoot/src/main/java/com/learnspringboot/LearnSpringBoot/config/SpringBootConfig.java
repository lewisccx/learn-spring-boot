package com.learnspringboot.LearnSpringBoot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
@Profile({"!dev"})
@Configuration
public class SpringBootConfig {

    @PostConstruct
    public void test(){
        System.out.println("loaded dev profile");
    }
}
