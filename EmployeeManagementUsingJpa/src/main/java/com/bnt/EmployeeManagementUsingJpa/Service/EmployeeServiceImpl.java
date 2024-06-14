package com.bnt.EmployeeManagementUsingJpa.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bnt.EmployeeManagementUsingJpa.Exception.DataIsNull;
import com.bnt.EmployeeManagementUsingJpa.Exception.DuplicateData;
import com.bnt.EmployeeManagementUsingJpa.Exception.UserNotFoundException;
import com.bnt.EmployeeManagementUsingJpa.Model.Employee;
import com.bnt.EmployeeManagementUsingJpa.Repository.EmployeeRespository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRespository employeeRespository;

    Logger logger=LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Override
    @CacheEvict(value="employee")
    public Employee saveEmployee(Employee employee)  {
            if(employee.getName()==null || employee.getSalary()==0 || employee.getName()==""){
                     throw new DataIsNull("Employee data is null or incomplete. Please provide the correct information.");  
             }
             Optional<Employee> optionalEntity = employeeRespository.findById(employee.getId());
             if (optionalEntity.isPresent()) {                 
                    throw new DuplicateData("Duplicate Data Id Alredy Exist");
             }
             else{
                       return employeeRespository.save(employee);  
             }
        
    }

    @Override
    @Cacheable(value = "employee", key = "#id")
    public Optional<Employee> getEmployeeById(int id) { 
        Optional<Employee> optionalEmployee = employeeRespository.findById(id);
        
        
        if(optionalEmployee.isPresent()){
                return optionalEmployee;           
            }
        else{
            throw new UserNotFoundException("User not Found ");
        }      
    }

    @Override
    @Cacheable(value = "employee", key = "'all'")
    public List<Employee> getAllEmployee() {      
         return employeeRespository.findAll();
    }

    @Override
    @CachePut(value = "employee", key = "#employee.id")
    public Employee updateEmployee(Employee employee) {

           if(employee.getName()==null || employee.getSalary()==0 || employee.getName()==""){
             throw new DataIsNull("Data is null fill all the data");  
             }
           Optional<Employee> optionalEmployee = employeeRespository.findById(employee.getId());    
            if(optionalEmployee.isPresent())
            {
               return employeeRespository.save(employee);
            }
            else{
               throw new UserNotFoundException("Id Not Found For Update employeeData");
            }
         }


         @Override
         @CacheEvict(value = "employee", key = "#id")
         public  void deleteEmployee(int id) {       
             Optional<Employee> optionalEmployee = employeeRespository.findById(id);        
             if (optionalEmployee.isPresent()) {
                 employeeRespository.deleteById(id);                 
             }
             else{
                 throw new UserNotFoundException("User not Found ");
             }
         }


   
    
        
  
    
}
