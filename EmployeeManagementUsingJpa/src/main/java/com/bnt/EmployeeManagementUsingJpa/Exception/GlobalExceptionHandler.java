package com.bnt.EmployeeManagementUsingJpa.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.bnt.EmployeeManagementUsingJpa.Response.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
   public ResponseEntity<ErrorResponse> handleStudentIdNotFound(UserNotFoundException ex) {
      //logger.error("Student Id Not Found {}", ex.getMessage());
      ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
      return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
   }
    
}
