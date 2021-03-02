package com.learnspringboot.LearnSpringBoot.exception.handler;

import com.learnspringboot.LearnSpringBoot.exception.custom.AppServiceException;
import com.learnspringboot.LearnSpringBoot.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private Response response;

    @Autowired
    public GlobalExceptionHandler(Response response) {
        this.response = response;
    }

    /*
        https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
        Override default response
        {
        "timestamp":"2019-02-27T04:03:52.398+0000",
        "status":500,
        "error":"Internal Server Error",
        "message":"...",
        "path":"/path"
        }
        General Exception
        status 500 for all exceptions
         */
    @ExceptionHandler(value = {NullPointerException.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
        response = Response.notFound();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /*
    Specific Exception
    status 400 for object Id not found exception
    */
    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Object> handleNullPointerException(NoSuchElementException ex, WebRequest request) {
        response = Response.badRequest();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }

    /*
      Custom Student Exception
      status 400 for student Id not found exception
      */
    @ExceptionHandler(value = {AppServiceException.StudentNotFoundException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(AppServiceException.StudentNotFoundException ex, WebRequest request) {
        response = Response.notFound();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    /*
     Custom Student Exception
     status 400 for Contact Id not found exception
     */
    @ExceptionHandler(value = {AppServiceException.ContactNotFoundException.class})
    public ResponseEntity<Object> handleContactNotFoundException(AppServiceException.ContactNotFoundException ex, WebRequest request) {
        response = Response.notFound();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
    /*
    Multiple Exception: Custom Student Exception & NullPointerException
    status 400 for student Id not found exception
    */
//    @ExceptionHandler(value = {NoSuchElementException.class, StudentServiceException.StudentNotFoundException.class})
//    public ResponseEntity<Object> handleSpecificException(Exception ex, WebRequest request) {
//        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage());
//        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
//
//    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleMissingPathVariable(ConstraintViolationException ex) {
        response = Response.validationException();
        response.addErrorMsgToResponse(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.valueOf(422));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        response = Response.validationException();
        response.addErrorMsgToResponse(ex.getMostSpecificCause().getCause().getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.valueOf(422));
    }

    // error handle for @Valid
    @Override
    protected ResponseEntity<Object>
    handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                 HttpHeaders headers,
                                 HttpStatus status, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());
        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        System.out.println(errors);
        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
    }
}




