package com.learnspringboot.LearnSpringBoot.award.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learnspringboot.LearnSpringBoot.student.model.Student;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Component
@Data
@Entity
@Table(name = "award")
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String name;

    private Integer amount;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "student_awards",
            joinColumns = {@JoinColumn(name = "award_id")},
            inverseJoinColumns = {@JoinColumn(name = "student_id")}
    )
    @JsonIgnore
    private Set<Student> studentSet = new HashSet<>();
}
