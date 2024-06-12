package com.bnt.EmployeeManagementUsingJpa.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bnt.EmployeeManagementUsingJpa.Exception.DataIsNull;
import com.bnt.EmployeeManagementUsingJpa.Exception.UserNotFoundException;
import com.bnt.EmployeeManagementUsingJpa.Model.Employee;

import com.bnt.EmployeeManagementUsingJpa.Service.EmployeeService;
import static org.springframework.http.HttpStatus.*;
import java.util.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock 
    EmployeeService employeeService;

    @InjectMocks
    EmployeeController employeeController;


    @Test
    void saveEmployeeTest(){
               Employee expected=new Employee(1,"atharva",21000);
               when(employeeService.saveEmployee(expected)).thenReturn(expected);
              ResponseEntity<Object> actualresultResponseEntity=employeeController.saveEmployee(expected);         
              assertEquals(CREATED, actualresultResponseEntity.getStatusCode());
    }

   
     @Test
    void getAllEmployees(){
       
         List<Employee> expectedlist = new ArrayList<>();
         expectedlist.add(new Employee(1, "atharva", 20000));
         expectedlist.add(new Employee(2, "ram", 40000));
     
         when(employeeService.getAllEmployee()).thenReturn(expectedlist);
         ResponseEntity<Object> actualrResponseEntity= employeeController.getAllEmployee();
     
         assertEquals(FOUND, actualrResponseEntity.getStatusCode());

    }

        @Test 
        void getEmployeeById(){
          Employee expected = new Employee(1, "atharva", 200000);
          Optional<Employee> optionalExpected = Optional.of(expected); 
      
          when(employeeService.getEmployeeById(1)).thenReturn(optionalExpected);
      
          ResponseEntity<Object> actualResultResponseEntity = employeeController.getEmployeeId(1);
          assertEquals(HttpStatus.OK, actualResultResponseEntity.getStatusCode());        
          assertEquals(expected, optionalExpected.get());
       }

          
        @Test
         void updateEmployee(){
            Employee expected=new Employee(1,"atharva",200000);
            when(employeeService.updateEmployee(expected)).thenReturn(expected);

           ResponseEntity<Object> actualResponseEntity=employeeController.updateEmployee(expected);
           assertEquals(OK, actualResponseEntity.getStatusCode());

         }

         @Test
         void deleteEmployee(){
            int id=1;
            doNothing().when(employeeService).deleteEmployee(id);
            ResponseEntity<Object> actualResponseEntity=employeeController.deleteEmployee(id);

           assertEquals(OK, actualResponseEntity.getStatusCode());
           assertEquals("User Deleted Successfully", actualResponseEntity.getBody());

         }





         //Negative Test Cases
         @Test
         void saveEmployeeTestNegative(){
                    Employee expected=new Employee(1,"atharva",0);
                    when(employeeService.saveEmployee(expected)).thenThrow(new DataIsNull("Data is null fill all the data"));          
                    assertThrows(DataIsNull.class, () -> employeeController.saveEmployee(expected));
                    verify(employeeService, times(1)).saveEmployee(expected);
         }

         @Test
         void getEmployeeByIdNegative(){
              int invalidId = 9999; 
               when(employeeService.getEmployeeById(invalidId)).thenThrow(new UserNotFoundException("User not Found"));
               assertThrows(UserNotFoundException.class, () -> employeeController.getEmployeeId(invalidId));
               verify(employeeService, times(1)).getEmployeeById(invalidId);

         }
           

         @Test
         void updateEmployeeNegative(){
          Employee expected=new Employee(1,"atharva",21000);
          when(employeeService.updateEmployee(expected)).thenThrow(new UserNotFoundException("Id Not Found For Uodate employeeData"));
          assertThrows(UserNotFoundException.class, () -> employeeController.updateEmployee(expected));
         }

         @Test
         void deleteEmployeeNegative(){

        int nonExistentId = 9999; 
        doThrow(new UserNotFoundException("User not found")).when(employeeService).deleteEmployee(nonExistentId);
        assertThrows(UserNotFoundException.class, () -> employeeController.deleteEmployee(nonExistentId));
        verify(employeeService, times(1)).deleteEmployee(nonExistentId);

         }

  
  
     



}


