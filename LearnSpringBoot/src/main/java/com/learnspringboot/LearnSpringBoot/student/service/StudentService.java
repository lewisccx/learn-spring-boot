package com.learnspringboot.LearnSpringBoot.student.service;

import com.learnspringboot.LearnSpringBoot.student.controller.request.StudentSignUpRequest;
import com.learnspringboot.LearnSpringBoot.student.model.Student;
import com.learnspringboot.LearnSpringBoot.student.model.dto.StudentsPassedAnySubjectDTO;

import java.util.List;
public interface StudentService {

    /**
     * Register a new student
     *
     * @param studentSignUpRequest
     * @return
     */
    Student signup(StudentSignUpRequest studentSignUpRequest);
    /**
     * @return
     */
    List<Student> findAll();
    /**
     * Update profile of the student
     *
     * @param student
     * @return
     */
    Student updateProfile(Student student, Integer studentId);
    /**
     * @param studentId
     * @return
     */
    Student findStudentById(Integer studentId);
    /**
     * @param studentId
     * @return
     */
    void deleteStudent(Integer studentId);
    List<StudentsPassedAnySubjectDTO> getStudentsPassedInAnySubject();
}
