package com.learnspringboot.LearnSpringBoot.assigment.service;

import com.learnspringboot.LearnSpringBoot.assigment.controller.request.NewAssignmentRequest;
import com.learnspringboot.LearnSpringBoot.assigment.model.Assignment;

import java.util.List;
public interface AssignmentService {

    /**
     * @param newAssignmentRequest
     * @return
     */
    Assignment addAssignment(Integer id, NewAssignmentRequest newAssignmentRequest);

    List<Assignment> findByStudentId(Integer studentId);
    /**
     * @param newAssignmentRequest
     * @param assignmentId
     * @param studentId
     * @return
     */
    Assignment updateAssignment(NewAssignmentRequest newAssignmentRequest, Integer studentId, Integer assignmentId);
    /**
     * @param assignmentId
     */
    void deleteAssignment(Integer assignmentId);
}
