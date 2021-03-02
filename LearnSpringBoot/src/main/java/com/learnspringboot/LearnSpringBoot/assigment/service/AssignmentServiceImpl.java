package com.learnspringboot.LearnSpringBoot.assigment.service;

import com.learnspringboot.LearnSpringBoot.assigment.controller.request.NewAssignmentRequest;
import com.learnspringboot.LearnSpringBoot.assigment.model.Assignment;
import com.learnspringboot.LearnSpringBoot.assigment.repository.AssignmentRepository;
import com.learnspringboot.LearnSpringBoot.exception.custom.AppServiceException;
import com.learnspringboot.LearnSpringBoot.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;

    private final StudentRepository studentRepository;

    private final Assignment assignment;

    @Autowired
    public AssignmentServiceImpl(AssignmentRepository assignmentRepository, StudentRepository studentRepository, Assignment assignment) {
        this.assignmentRepository = assignmentRepository;
        this.studentRepository = studentRepository;
        this.assignment = assignment;
    }

    @Override
    public Assignment addAssignment(Integer id, NewAssignmentRequest newAssignmentRequest) {
        return studentRepository.findById(id)
                .map(student -> {
                    assignment.setName(newAssignmentRequest.getName());
                    assignment.setGrade(newAssignmentRequest.getGrade());
                    assignment.setStudent(student);
                    return assignmentRepository.save(assignment);
                }).orElseThrow(() -> new AppServiceException.StudentNotFoundException(id));
    }

    @Override
    public List<Assignment> findByStudentId(Integer studentId) {
        return assignmentRepository.findAllAssignmentsByStudentId(studentId);

    }

    @Override
    public Assignment updateAssignment(NewAssignmentRequest update, Integer studentId, Integer assignmentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new AppServiceException.StudentNotFoundException(studentId);
        }
        return assignmentRepository.findById(assignmentId)
                .map(assignment -> {
                    assignment.setName(update.getName());
                    assignment.setGrade(update.getGrade());
                    return assignmentRepository.save(assignment);
                }).orElseThrow(() -> new AppServiceException.AssignmentNotFoundException(assignmentId));
    }

    @Override
    public void deleteAssignment(Integer assignmentId) {
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        if (assignment.isPresent()) {
            assignmentRepository.deleteById(assignmentId);
            return;
        }
        throw new AppServiceException.AssignmentNotFoundException(assignmentId);
    }
}
