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
     
         when(employeeRespository.findAll()).thenReturn(expectedlist);
     
         List<Employee> actualList = employeeServiceImpl.getAllEmployee();
         assertEquals(expectedlist, actualList);
    }

    @Test
    void getEmployeeById(){
        Employee expected= new Employee(1,"atharva",20000);
        Optional<Employee> optionalExpected = Optional.of(expected);

         when(employeeRespository.findById(1)).thenReturn(optionalExpected);
        
         Optional<Employee> optionalactual=employeeServiceImpl.getEmployeeById(1);
        assertEquals(optionalExpected, optionalactual);
    }



    @Test
    void updateEmployee(){

    Employee expected = new Employee(1, "Atharva", 20000);

    when(employeeRespository.findById(expected.getId())).thenReturn(Optional.of(expected));
    when(employeeRespository.save(expected)).thenReturn(expected);

    Employee actual = employeeServiceImpl.updateEmployee(expected);
    assertEquals(expected, actual);

}

    
@Test
void testDeleteEmployeeService() {
    int id = 1;
    Employee employee = new Employee(id, "Atharva", 20000);
    when(employeeRespository.findById(id)).thenReturn(Optional.of(employee));

    employeeServiceImpl.deleteEmployee(id);

    verify(employeeRespository, times(1)).findById(id);
    verify(employeeRespository, times(1)).deleteById(id);
}


    
}

