package com.learnspringboot.LearnSpringBoot.student.controller.api;

import com.learnspringboot.LearnSpringBoot.response.Response;
import com.learnspringboot.LearnSpringBoot.student.controller.request.StudentSignUpRequest;
import com.learnspringboot.LearnSpringBoot.student.model.Student;
import com.learnspringboot.LearnSpringBoot.student.model.dto.StudentsPassedAnySubjectDTO;
import com.learnspringboot.LearnSpringBoot.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
@RestController
@RequestMapping("/api/v1")
@Validated
public class StudentController {

    //https://dzone.com/articles/spring-di-patterns-the-good-the-bad-and-the-ugly
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping(path = "/students/register")
    public ResponseEntity<Student> registerStudent(@Valid @RequestBody StudentSignUpRequest studentSignUpRequest) {
        Student std = studentService.signup(studentSignUpRequest);
        return new ResponseEntity<>(std, HttpStatus.CREATED);
    }

    @GetMapping(path = "/students/all")
    public ResponseEntity<List<Student>> findAll() {
        List<Student> studentList = studentService.findAll();
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

    @GetMapping(path = "/students/{id}")
    public Response findById(@PathVariable("id") @Min(1) final Integer id) {
        Student std = studentService.findStudentById(id);
        return Response.ok().setPayload(std);
    }

    @GetMapping(path = "/students/passed")
    public List<StudentsPassedAnySubjectDTO> findStudentsPassedInAnySubject() {
        return studentService.getStudentsPassedInAnySubject();
    }

    @PutMapping(path = "/students/{id}")
    public Response UpdateStudent(@Valid @RequestBody Student update, @PathVariable("id") @Min(1) Integer id) {
        Student std = studentService.updateProfile(update, id);
        return Response.ok().setPayload(std);
    }

    @DeleteMapping(path = "/students/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") @Min(1) Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

}
