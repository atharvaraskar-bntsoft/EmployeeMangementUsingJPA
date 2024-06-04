package com.bnt.EmployeeManagementUsingJpa.Exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String msg){
        super(msg);
    }
    
}
