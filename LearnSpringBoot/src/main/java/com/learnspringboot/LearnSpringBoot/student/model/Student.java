package com.learnspringboot.LearnSpringBoot.student.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learnspringboot.LearnSpringBoot.assigment.model.Assignment;
import com.learnspringboot.LearnSpringBoot.award.model.Award;
import com.learnspringboot.LearnSpringBoot.contact.model.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/*
  https://www.baeldung.com/intro-to-project-lombok
  @Data bundles the features of
  @ToString ,
  @EqualsAndHashCode ,
  @Getter /
  @Setter and
  @RequiredArgsConstructor together
 */

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student")
@Component
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
//  https://www.baeldung.com/jpa-cascade-types
//  Persist: both parent and child cannot be deleted
//  Remove: Parent deleted, child also get deleted; Child deleted, parent remains
//  Detach: Parent cannot be deleted unless child is deleted
//  Refresh: Parent cannot be deleted unless child is deleted
//  MERGE: Parent cannot be deleted unless child is deleted
    /*
    mappedBy value must be same as Student field in Contact model
    FetchType.LAZY or EAGER does not work on Parent in one to one relationship
    Only work Child
    https://www.baeldung.com/hibernate-lazy-eager-loading
     */
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Contact contact;

//    FetchType.LAZY or EAGER does not work
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Assignment> assignmentSet;
//    FetchType.LAZY or EAGER does not work
    @ManyToMany(mappedBy = "studentSet",  cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<Award> awardSet = new HashSet<>();
}
