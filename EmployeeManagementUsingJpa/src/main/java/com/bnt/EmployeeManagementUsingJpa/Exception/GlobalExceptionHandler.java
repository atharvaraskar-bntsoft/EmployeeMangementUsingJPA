package com.bnt.EmployeeManagementUsingJpa.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bnt.EmployeeManagementUsingJpa.Response.ErrorResponse;


@ControllerAdvice
public class GlobalExceptionHandler {

   

   @ExceptionHandler(UserNotFoundException.class)
   public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e) {
           ErrorResponse errorResponse=new ErrorResponse(e.getMessage(),HttpStatus.NOT_FOUND.value());
            return  new ResponseEntity<Object>(errorResponse,HttpStatus.NOT_FOUND);
          }  
       
   @ExceptionHandler(DuplicateData.class)       
   public ResponseEntity<Object> handleDuplicateData( DuplicateData e){
            ErrorResponse errorResponse=new ErrorResponse(e.getMessage(),HttpStatus.ALREADY_REPORTED.value());
            return new ResponseEntity<Object>(errorResponse,HttpStatus.ALREADY_REPORTED);
   }
   
   @ExceptionHandler(DataIsNull.class)
     public ResponseEntity<Object> handleDataIsNullData( DataIsNull e){
            ErrorResponse errorResponse=new ErrorResponse(e.getMessage(),HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
     }


   }
           

