
                            // Controller

package com.springboot.employee.controller;

import com.springboot.employee.entity.Employe;
import com.springboot.employee.exception.EmployeNotFoundException;
import com.springboot.employee.exception.ListEmployeEmptyException;
import com.springboot.employee.request.EmployeRequest;
import com.springboot.employee.service.EmployeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;



import io.swagger.v3.oas.annotations.security.SecurityRequirement;  // Importing necessary annotations for Swagger documentation
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/employes")
@Tag(name = "Employe API", description = "API for managing employees")
@SecurityRequirement(name = "bearerAuth") // help to make visible the security requirement in Swagger UI
public class EmployeController {

    private final EmployeService employeService;

    @Autowired
    public EmployeController(EmployeService employeService) {
        this.employeService = employeService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all employees", description = "Retrieve a list of all employees")
    public List<Employe> getAll() {
        List<Employe> employes =  employeService.getAllEmployees();
        if(employes.isEmpty())
        {
            throw new ListEmployeEmptyException("List of employees is empty");

        }

        return employes;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // 200 OK if the employee is found, or 404 Not Found if the employee does not exist
    @Operation(summary = "Get employee by ID", description = "Retrieve an employee by their ID")
    public Employe getById(@PathVariable @Min(value = 1) Long id) {
        Employe employe = employeService.getEmployeeById(id);
        if (employe == null) {
            throw new EmployeNotFoundException("Employee with ID " + id + " not found");
        }
        return employe;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201 Created if the employee is created successfully, or 400 Bad Request if the request is invalid
    @Operation(summary = "Create a new employee", description = "Create a new employee with the provided details")
    public Employe create(@RequestBody @Valid EmployeRequest employeRequest) {
        if (employeRequest.getFirstName() == null || employeRequest.getLastName() == null || employeRequest.getEmail() == null) {
            throw new IllegalArgumentException("First name, last name, and email are required");
        }
        return employeService.createEmployee(employeRequest);
    }


    @PutMapping("/{id}")
    // @ResponseStatus(HttpStatus.NO_CONTENT) // 204 No Content if the employee is updated successfully, or 404 Not Found if the employee does not exist
    @ResponseStatus(HttpStatus.OK) // to return the updated employee with 200 OK status
    @Operation(summary = "Update an existing employee", description = "Update the details of an existing employee by ID")
    public Employe update(@PathVariable @Min(value = 1) Long id, @RequestBody @Valid EmployeRequest updatedData) {

        Employe updatedEmploye = employeService.updateEmployee(id, updatedData);
        if (updatedEmploye == null) {
            throw new EmployeNotFoundException("Employee with ID " + id + " not found");
        }
        return updatedEmploye;

    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204 No Content if the employee is deleted successfully, or 404 Not Found if the employee does not exist
    @Operation(summary = "Delete an employee", description = "Delete an employee by their ID")
    public void delete(@PathVariable @Min(value = 1) Long id) {
        Employe employe = employeService.getEmployeeById(id);
        if (employe == null) {
            throw new EmployeNotFoundException("Employee with ID " + id + " not found");
        }
        employeService.deleteEmployee(id);
    }


}
