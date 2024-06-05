package com.bnt.EmployeeManagementUsingJpa.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.bnt.EmployeeManagementUsingJpa.Model.Employee;
import com.bnt.EmployeeManagementUsingJpa.Repository.EmployeeRespository;
import java.util.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    EmployeeRespository employeeRespository;

    @InjectMocks
    EmployeeServiceImpl employeeServiceImpl;

    @Test
    void saveEmployee(){
        Employee expected= new Employee(1,"atharva",20000);

        when(employeeRespository.save(expected)).thenReturn(expected);
        Employee actual= employeeServiceImpl.saveEmployee(expected);

        assertEquals(expected, actual);
    }

    @Test
    void getAllEmployee(){
         List<Employee> expectedlist = new ArrayList<>();
         expectedlist.add(new Employee(1, "atharva", 20000));
         expectedlist.add(new Employee(2, "ram", 40000));
     
         // Mocking the behavior of the service method
         when(employeeRespository.findAll()).thenReturn(expectedlist);
     
         // Calling the controller method under test
         List<Employee> actualList = employeeServiceImpl.getAllEmployee();
     
         // Asserting that the expected and actual lists are equal
         assertEquals(expectedlist, actualList);
    }

    @Test
    void updateEmployee(){
        Employee expected=new Employee(1,"Atharva",20000);
        when(employeeRespository.save(expected)).thenReturn(expected);

        Employee actual=employeeServiceImpl.updateEmployee(expected);
        assertEquals(expected, actual);        
    }

    @Test
    void deleteEmployee(){
       
        int id=1;
        employeeRespository.deleteById(id);
        verify(employeeServiceImpl,times(1)).deleteEmployee(id);


    }
    
}

