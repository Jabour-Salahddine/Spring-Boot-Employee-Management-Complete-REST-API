package com.springboot.employee.exception;

public class ListEmployeEmptyException extends RuntimeException {

    public ListEmployeEmptyException(String message) {
        super(message);
    }

    public ListEmployeEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
