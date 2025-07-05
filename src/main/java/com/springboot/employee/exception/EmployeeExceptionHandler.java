package com.springboot.employee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EmployeErrorResponse> handleException(EmployeNotFoundException exception) {
        EmployeErrorResponse error = new EmployeErrorResponse(
                HttpStatus.NOT_FOUND.value(), // 404 Not Found
                exception.getMessage(), // Custom message from the exception
                (int) System.currentTimeMillis() // Current timestamp
        );
           return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); // Return the error response with 404 status
    }

    @ExceptionHandler
    public ResponseEntity<EmployeErrorResponse> handleException(ListEmployeEmptyException exception) {
        EmployeErrorResponse error = new EmployeErrorResponse(
                HttpStatus.NOT_FOUND.value(), // 404 Not Found
                exception.getMessage(), // Custom message from the exception
                (int) System.currentTimeMillis() // Current timestamp
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); // Return the error response with 404 status
    }


    @ExceptionHandler
    public ResponseEntity<EmployeErrorResponse> handleException(Exception exception) {
        EmployeErrorResponse error = new EmployeErrorResponse(
                HttpStatus.BAD_REQUEST.value(), // 400 Bad Request
                exception.getMessage(), // Custom message from the exception
                (int) System.currentTimeMillis() // Current timestamp
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); // Return the error response with 400 status
    }


    //************************************************************************

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<EmployeErrorResponse> handleAccessDeniedException(AccessDeniedException ex) {
        EmployeErrorResponse error = new EmployeErrorResponse(
                HttpStatus.FORBIDDEN.value(), // 403 Forbidden
                "Access Denied: You do not have permission to perform this action.",
                (int) System.currentTimeMillis()
        );
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<EmployeErrorResponse> handleAuthenticationException(AuthenticationException ex) {
        EmployeErrorResponse error = new EmployeErrorResponse(
                HttpStatus.UNAUTHORIZED.value(), // 401 Unauthorized
                "Authentication Failed: " + ex.getMessage(),
                (int) System.currentTimeMillis()
        );
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }


    /*
    // Votre handler générique
    @ExceptionHandler(Exception.class)
    public ResponseEntity<EmployeErrorResponse> handleGenericException(Exception exception) {
        EmployeErrorResponse error = new EmployeErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), // 500 Internal Server Error est plus approprié
                "An unexpected error occurred: " + exception.getMessage(),
                (int) System.currentTimeMillis()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    */



}




