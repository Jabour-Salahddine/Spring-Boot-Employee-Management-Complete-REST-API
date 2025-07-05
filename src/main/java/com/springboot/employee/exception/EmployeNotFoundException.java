package com.springboot.employee.exception;

public class EmployeNotFoundException extends RuntimeException {


    public EmployeNotFoundException(String message) {
        super(message);
    }

    public EmployeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
