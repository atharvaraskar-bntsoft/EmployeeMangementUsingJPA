package com.bnt.EmployeeManagementUsingJpa.Repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bnt.EmployeeManagementUsingJpa.Model.Employee;

@Repository
public interface EmployeeRespository extends JpaRepository<Employee,Integer> {

}
