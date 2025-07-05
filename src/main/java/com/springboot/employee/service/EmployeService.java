
                             // Service

package com.springboot.employee.service;
import com.springboot.employee.entity.Employe;
import com.springboot.employee.request.EmployeRequest;

import java.util.List;

public interface EmployeService {
    // Define the methods that will be implemented by the service class
    // For example:

    List<Employe> getAllEmployees();
    Employe getEmployeeById(Long id);
    Employe createEmployee(EmployeRequest employeRequest);
    Employe updateEmployee(Long id, EmployeRequest employeRequest);
    void deleteEmployee(Long id);



    // These methods will be implemented in a class that implements this interface

}
