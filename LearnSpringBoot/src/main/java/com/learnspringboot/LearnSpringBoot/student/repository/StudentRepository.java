package com.learnspringboot.LearnSpringBoot.student.repository;

import com.learnspringboot.LearnSpringBoot.student.model.Student;
import com.learnspringboot.LearnSpringBoot.student.model.dto.StudentsPassedAnySubjectDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    //https://stackoverflow.com/questions/53322203/spring-boot-data-query-to-dto
    //@Query("select new com.learnspringboot.LearnSpringBoot.student.model.dto.StudentsPassedAnySubject(s.firstName, s.lastName, a.name) from Student s JOIN Assignment a on s.id = a.student.id where a.grade > 40")
    @Query(value = "select  s.first_name AS firstName, s.last_name AS lastName from Student s INNER JOIN Assignment a on s.id = a.student_id where a.grade > 40", nativeQuery = true)
    //No converter type error if DTO is a class object instead of interface
    List<StudentsPassedAnySubjectDTO> getStudentsPassedInAnySubject();

}
