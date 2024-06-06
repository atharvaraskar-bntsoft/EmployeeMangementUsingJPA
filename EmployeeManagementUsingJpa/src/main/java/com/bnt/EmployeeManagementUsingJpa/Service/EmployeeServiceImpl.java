package com.bnt.EmployeeManagementUsingJpa.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Employee saveEmployee(Employee employee) {
        Employee emp=null;

        try {
            if(employee.getName()==null || employee.getSalary()==0){
                throw new DataIsNull("Data is null fill all the data");  
             }
             Optional<Employee> optionalEntity = employeeRespository.findById(employee.getId());
             if (optionalEntity.isPresent()) {
                    throw new DuplicateData("Duplicate Data Id Alredy Exist");
             }
             else{
                return employeeRespository.save(employee);
             }
            
        } catch (Exception e) {
            logger.error("Excption is:"+e);
        }
        return emp;    
        
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) { 
        Optional<Employee> optionalEmployee = employeeRespository.findById(id);
        try{
            if(!optionalEmployee.isPresent()){
                optionalEmployee=null;
                throw new UserNotFoundException("User not Found ");
            }
       
        }catch(UserNotFoundException e){
             logger.error("UserNotfoundException",e);
        }
        return optionalEmployee;
        
    }

    @Override
    public List<Employee> getAllEmployee() {
        
         return employeeRespository.findAll();
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee emp=null;
        try{
        Optional<Employee> optionalEmployee = employeeRespository.findById(employee.getId()); 
  
        if (optionalEmployee.isPresent()) {
            emp=employeeRespository.save(employee);
            return emp;         
        }
        else{
            throw new UserNotFoundException("User not Found ");
        }
    }
        catch(UserNotFoundException e){
            logger.error("Exception occurs-"+e.getMessage());
       }
       return  emp;
    }

    @Override
    public  boolean deleteEmployee(int id) {
        Boolean result=false;
        try{
        Optional<Employee> optionalEmployee = employeeRespository.findById(id); 
  
        if (optionalEmployee.isPresent()) {
            employeeRespository.deleteById(id);
            result=true;
            return result;         
        }
        else{
            throw new UserNotFoundException("User not Found ");
        }
    }
        catch(UserNotFoundException e){
            logger.error("Exception occurs-"+e.getMessage());
       }
       return  result;
    }
        
  
    
}
