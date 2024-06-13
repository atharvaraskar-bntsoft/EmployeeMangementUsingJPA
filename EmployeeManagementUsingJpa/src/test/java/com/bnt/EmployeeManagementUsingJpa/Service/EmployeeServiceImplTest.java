package com.bnt.EmployeeManagementUsingJpa.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.bnt.EmployeeManagementUsingJpa.Exception.DataIsNull;
import com.bnt.EmployeeManagementUsingJpa.Exception.UserNotFoundException;
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






//negative test cases

@Test
void saveEmployeeTestNegative(){
        Employee employee=new Employee(1,"",0);
        assertThrows(DataIsNull.class, () -> employeeServiceImpl.saveEmployee(employee));
        verify(employeeRespository, never()).save(employee);
     // verify(employeeRespository, never()).save(any());
    }



@Test
void getEmployeeByIdTestNegative(){
    int InvalidId=9999;
    when(employeeRespository.findById(InvalidId)).thenReturn(Optional.empty()); 
    assertThrows(UserNotFoundException.class,()->employeeServiceImpl.getEmployeeById(InvalidId));
}
    

@Test
void getAllEmployeeNegative(){
    List<Employee> expected=Collections.emptyList();
    when(employeeRespository.findAll()).thenReturn(expected);
   // when(employeeRespository.findAll()).thenReturn(Collections.emptyList());
    List<Employee> actual = employeeServiceImpl.getAllEmployee();
   // assertEquals(Collections.emptyList(), result);
   assertEquals(expected, actual);

}


@Test
void updateEmployeeNegative(){
    Employee employee=new Employee(1,"atharva",10000);
    assertThrows(UserNotFoundException.class, () -> employeeServiceImpl.updateEmployee(employee));
    verify(employeeRespository, never()).save(employee);
    //verify(employeeRespository, never()).save(any());   
}


@Test
void deleteEmployeeNegative(){
     int id=9999;
     when(employeeRespository.findById(id)).thenReturn(Optional.empty());
     //when(employeeRespository.findById(anyInt())).thenReturn(Optional.empty());
     assertThrows(UserNotFoundException.class, () -> employeeServiceImpl.deleteEmployee(id));
     verify(employeeRespository, never()).deleteById(id);

}

}
