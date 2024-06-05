package com.bnt.EmployeeManagementUsingJpa.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.bnt.EmployeeManagementUsingJpa.Model.Employee;
import com.bnt.EmployeeManagementUsingJpa.Service.EmployeeService;
import static org.springframework.http.HttpStatus.*;
import java.util.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmplloyeeControllerTest {

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
            assertEquals(expected, actualresultResponseEntity.getBody());

    }

     @Test
    void getAllEmployees(){
       
         List<Employee> expectedlist = new ArrayList<>();
         expectedlist.add(new Employee(1, "atharva", 20000));
         expectedlist.add(new Employee(2, "ram", 40000));
     
         // Mocking the behavior of the service method
         when(employeeService.getAllEmployee()).thenReturn(expectedlist);
     
         // Calling the controller method under test
         List<Employee> actualList = employeeController.getAllEmployee();
     
         // Asserting that the expected and actual lists are equal
         assertEquals(expectedlist, actualList);

    }

    //     @Test 
    //     void getEmployeeById(){
    //          Employee expected=new Employee(1,"atharva",200000);
    //          when(employeeService.getEmployeeById(1)).thenReturn(expected);

    //     ResponseEntity<Object> actualresultResponseEntity=employeeController.getEmployeeId(1);
    //     assertEquals(OK, actualresultResponseEntity.getStatusCode());
    //     assertEquals(expected, actualresultResponseEntity.getBody());
    //    }

          
        @Test
         void updateEmployee(){
            Employee expected=new Employee(1,"atharva",200000);
            when(employeeService.updateEmployee(expected)).thenReturn(expected);

           ResponseEntity<Object> actualResponseEntity=employeeController.updatEmployee(expected);

           assertEquals(OK, actualResponseEntity.getStatusCode());
           assertEquals(expected, actualResponseEntity.getBody());

         }

         @Test
         void deleteEmployee(){
            Employee expected=new Employee(1,"atharva",200000);
            when(employeeService.deleteEmployee(1)).thenReturn(true);

            ResponseEntity<Object> actualResponseEntity=employeeController.deleteEmployee(1);

           assertEquals(OK, actualResponseEntity.getStatusCode());
           assertEquals("User Deleted Successfully", actualResponseEntity.getBody());


         }



}


