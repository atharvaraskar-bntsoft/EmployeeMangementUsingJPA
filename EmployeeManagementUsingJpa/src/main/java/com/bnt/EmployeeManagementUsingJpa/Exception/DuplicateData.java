package com.bnt.EmployeeManagementUsingJpa.Exception;

public class DuplicateData extends RuntimeException {
    
       public DuplicateData(String str){
                super(str);
       }
}
