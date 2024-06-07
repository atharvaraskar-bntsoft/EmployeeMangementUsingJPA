package com.bnt.EmployeeManagementUsingJpa.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class SuccessResponse {
    
    private String message;
    private int statuscode;
    Object object;
}
