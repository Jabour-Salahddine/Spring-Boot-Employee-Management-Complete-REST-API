package com.springboot.employee.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// This class is a placeholder for request data related to employees.
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeRequest {

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Email(message = "Email format is invalid")
    @NotBlank(message = "Email is required")
    private String email;

}
