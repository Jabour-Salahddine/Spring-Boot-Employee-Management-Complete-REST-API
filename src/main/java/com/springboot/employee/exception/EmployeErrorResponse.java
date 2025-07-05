package com.springboot.employee.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeErrorResponse {

    private int status;
    private String message;
    private int timeStamp;

}
