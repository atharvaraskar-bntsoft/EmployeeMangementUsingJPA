package com.bnt.EmployeeManagementUsingJpa.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    Employee savEmployee(@RequestBody Employee employee){
              return  employeeService.saveEmployee(employee);
    }

    @GetMapping
    List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

    @GetMapping("/{id}")
    Optional<Employee> getEmployeeId(@PathVariable("id") int id){
        return employeeService.getEmployeeById(id);
    }

    @PutMapping
    public Employee updatEmployee(@RequestBody Employee employee){
        return employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") int id){
         employeeService.deleteEmployee(id);
    }


    
}
