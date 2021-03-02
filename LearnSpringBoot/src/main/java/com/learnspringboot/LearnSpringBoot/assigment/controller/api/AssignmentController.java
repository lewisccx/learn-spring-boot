package com.learnspringboot.LearnSpringBoot.assigment.controller.api;

import com.learnspringboot.LearnSpringBoot.assigment.controller.request.NewAssignmentRequest;
import com.learnspringboot.LearnSpringBoot.assigment.model.Assignment;
import com.learnspringboot.LearnSpringBoot.assigment.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
@RestController
@RequestMapping("/api/v1")
public class AssignmentController {

    private final AssignmentService assignmentService;

    @Autowired
    public AssignmentController(AssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @GetMapping("/student/{studentId}/assignments")
    public ResponseEntity<List<Assignment>> getAssignmentByStudentId(@PathVariable("studentId") @Min(1) Integer studentId) {
        List<Assignment> assignmentList = assignmentService.findByStudentId(studentId);
        return new ResponseEntity<>(assignmentList, HttpStatus.OK);
    }

    @PostMapping("/student/{studentId}/assignments")
    public ResponseEntity<Assignment> addAssignment(@PathVariable("studentId") @Min(1) Integer studentId,
                                                    @Valid @RequestBody NewAssignmentRequest newAssignmentRequest) {
        Assignment assignment = assignmentService.addAssignment(studentId, newAssignmentRequest);
        return new ResponseEntity<>(assignment, HttpStatus.CREATED);
    }

    @PutMapping("/student/{studentId}/assignment/{assignmentId}")
    public ResponseEntity<Assignment> updateAssignment(@PathVariable("studentId") @Min(1) Integer studentId,
                                                       @PathVariable("assignmentId") @Min(1) Integer assignmentId,
                                                       @Valid @RequestBody NewAssignmentRequest assignmentRequest) {
        Assignment assignment = assignmentService.updateAssignment(assignmentRequest, studentId, assignmentId);
        return new ResponseEntity<>(assignment, HttpStatus.OK);

    }

    @DeleteMapping("/assignments/{assignmentId}")
    public ResponseEntity<Void> deleteAssignment(
            @PathVariable("assignmentId") @Min(1) Integer assignmentId) {
        assignmentService.deleteAssignment(assignmentId);
        return ResponseEntity.noContent().build();

    }
}
