package com.bnt.EmployeeManagementUsingJpa.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EmployeeTest {

     @Test
    void setandgetId(){
           Employee employee= new Employee();

           employee.setId(4);
           assertEquals(4, employee.getId());
    }

    @Test
    void setandgetName(){
        Employee employee= new Employee();

        employee.setName("virat");
        assertEquals("virat", employee.getName());

    }

    @Test
    void setandgetSalary(){
        Employee employee= new Employee();

        employee.setSalary(4000);
        assertEquals(4000, employee.getSalary());

    }
    
}
