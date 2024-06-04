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
import com.bnt.EmployeeManagementUsingJpa.Service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    ResponseEntity<Object> savEmployee(@RequestBody Employee employee){
        Employee emp= employeeService.saveEmployee(employee); 
           if(emp ==null){
               return new ResponseEntity<>("Invalid Data: Null or Duplicate entries detected. Verify input",HttpStatus.BAD_REQUEST);
           }
           else{
                return new ResponseEntity<>(emp,HttpStatus.ACCEPTED);
           }             
    }

    @GetMapping
    List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/{id}")
    ResponseEntity<Object> getEmployeeId(@PathVariable("id") int id){
        Optional<Employee> optionalEmployee =employeeService.getEmployeeById(id);
        if (optionalEmployee== null) {
            return  new ResponseEntity<Object>("USER NOT FOUND ",HttpStatus.NOT_FOUND);
           }
        else{
            return  new ResponseEntity<Object>(optionalEmployee,HttpStatus.OK);
        }
    }

    @PutMapping
    public ResponseEntity<Object> updatEmployee(@RequestBody Employee employee){
        Employee emp = employeeService.updateEmployee(employee);
        if (emp== null) {
            return  new ResponseEntity<Object>("USER NOT FOUND ",HttpStatus.NOT_FOUND);
           }
        else{
            return  new ResponseEntity<Object>(emp,HttpStatus.OK);
        }

       
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") int id){
         employeeService.deleteEmployee(id);
    }


    
}
