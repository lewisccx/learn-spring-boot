package com.learnspringboot.LearnSpringBoot.student.service;

import com.learnspringboot.LearnSpringBoot.exception.custom.AppServiceException;
import com.learnspringboot.LearnSpringBoot.student.controller.request.StudentSignUpRequest;
import com.learnspringboot.LearnSpringBoot.student.model.Student;
import com.learnspringboot.LearnSpringBoot.student.model.dto.StudentsPassedAnySubjectDTO;
import com.learnspringboot.LearnSpringBoot.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final Student student;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, Student student ) {
        this.studentRepository = studentRepository;
        this.student = student;
    }


    @Override
    public Student signup(StudentSignUpRequest studentSignUpRequest) {
        //OAuth
        //Continental Single-SignOn
        student.setFirstName(studentSignUpRequest.getFirstName());
        student.setLastName(studentSignUpRequest.getLastName());
        return studentRepository.save(student);

    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateProfile(Student update, Integer studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            student.get().setFirstName(update.getFirstName());
            student.get().setLastName(update.getLastName());

        }
        return studentRepository.save(student.get());

    }

    @Override
    public Student findStudentById(Integer studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            return student.get();
        }
        throw new AppServiceException.StudentNotFoundException(studentId);
    }

    @Override
    public void deleteStudent(Integer studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            studentRepository.deleteById(studentId);
            return;
        }
        throw new AppServiceException.StudentNotFoundException(studentId);

    }

    @Override
    public List<StudentsPassedAnySubjectDTO> getStudentsPassedInAnySubject() {
        return studentRepository.getStudentsPassedInAnySubject();
    }

}
