package com.learnspringboot.LearnSpringBoot.assigment.repository;

import com.learnspringboot.LearnSpringBoot.assigment.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    List<Assignment> findAllAssignmentsByStudentId(Integer studentId);
    //List<Assignment> findByStudent_StudentName(Integer studentId);

}
