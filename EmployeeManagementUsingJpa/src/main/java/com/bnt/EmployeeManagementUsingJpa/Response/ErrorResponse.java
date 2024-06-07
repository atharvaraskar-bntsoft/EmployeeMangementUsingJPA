package com.bnt.EmployeeManagementUsingJpa.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ErrorResponse {

    private String message;
    private int statuscode;
    
}
