package com.bnt.EmployeeManagementUsingJpa.Service;

import java.util.List;
import java.util.Optional;

import com.bnt.EmployeeManagementUsingJpa.Model.Employee;

public interface EmployeeService {

     public Employee saveEmployee( Employee employee);

     public Optional<Employee> getEmployeeById(int id);

     public List<Employee> getAllEmployee();

     public Employee updateEmployee(Employee employee);

     public void deleteEmployee(int id);

   

}
