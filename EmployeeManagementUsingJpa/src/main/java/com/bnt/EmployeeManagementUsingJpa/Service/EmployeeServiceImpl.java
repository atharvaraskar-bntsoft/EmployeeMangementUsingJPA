package com.bnt.EmployeeManagementUsingJpa.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bnt.EmployeeManagementUsingJpa.Model.Employee;
import com.bnt.EmployeeManagementUsingJpa.Repository.EmployeeRespository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRespository employeeRespository;

    @Override
    public Employee saveEmployee(Employee employee) {
           return  employeeRespository.save(employee); 
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {    
        return employeeRespository.findById(id);
    }

    @Override
    public List<Employee> getAllEmployee() {
        
         return employeeRespository.findAll();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        
       return employeeRespository.save(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        
         employeeRespository.deleteById(id);

    }

   
    
}
