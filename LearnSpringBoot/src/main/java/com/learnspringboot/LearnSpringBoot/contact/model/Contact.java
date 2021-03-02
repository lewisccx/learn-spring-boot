package com.learnspringboot.LearnSpringBoot.contact.model;

import com.learnspringboot.LearnSpringBoot.student.model.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import javax.persistence.*;
@Component
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer mobile;

    private String email;
    /*
    exclude student properties: add @JsonIgnore
    include student properties:  remove @JsonIgnore FetchType set to EAGER
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "student_id")
    private Student student;
}
