package com.bnt.EmployeeManagementUsingJpa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import com.bnt.EmployeeManagementUsingJpa.Model.Employee;
import com.bnt.EmployeeManagementUsingJpa.Response.SuccessResponse;
import com.bnt.EmployeeManagementUsingJpa.Service.EmployeeService;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    Logger logger=LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping
    ResponseEntity<Object> saveEmployee(@RequestBody Employee employee){
        Employee emp= employeeService.saveEmployee(employee); 
        SuccessResponse successResponse=new SuccessResponse("The user is created",HttpStatus.CREATED.value(),emp)     ;   
        logger.info("Employee saved successfully: {}",emp);
        return new ResponseEntity<>(successResponse,HttpStatus.CREATED);           
    }

    @GetMapping
     ResponseEntity<Object> getAllEmployee(){
        List<Employee> list1= employeeService.getAllEmployee();
        logger.info("Retrieving information of all employees");
        SuccessResponse successResponse =new SuccessResponse("Showing Information Of All Employees",HttpStatus.FOUND.value(),list1);
        return new ResponseEntity<>(successResponse,HttpStatus.FOUND);
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getEmployeeId(@PathVariable("id") int id){
        Optional<Employee> optionalEmployee =employeeService.getEmployeeById(id);
       logger.info("Retrieving information for employee with ID: {}",id);
        SuccessResponse successResponse=new SuccessResponse("Showing Information Of Employee",HttpStatus.FOUND.value(),optionalEmployee);
        return  new ResponseEntity<Object>(successResponse,HttpStatus.OK);
     }
    

    @PutMapping
    ResponseEntity<Object> updateEmployee(@RequestBody Employee employee){
        Employee emp= employeeService.updateEmployee(employee);
        logger.info("Updating employee with ID: {}", employee.getId());
        SuccessResponse successResponse =new SuccessResponse();
        successResponse.setMessage("Data updated Successfully");
        successResponse.setStatuscode(HttpStatus.OK.value());
        successResponse.setEmployee(emp);
        return new ResponseEntity<>(successResponse,HttpStatus.OK);
    }
    

 
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable("id") int id){
            employeeService.deleteEmployee(id);
            logger.info("Employee with ID {} deleted successfully", id);
            return  new ResponseEntity<Object>("User Deleted Successfully",HttpStatus.OK);
        
    }


    
}
