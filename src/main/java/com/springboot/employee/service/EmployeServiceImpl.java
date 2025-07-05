package com.springboot.employee.service;

import com.springboot.employee.entity.Employe;
import com.springboot.employee.repository.EmployeRepository;
import com.springboot.employee.request.EmployeRequest;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service // This annotation indicates that this class is a Spring service component : logic for business operations
public class EmployeServiceImpl implements EmployeService {


  // here we use constructor injection to inject the EmployeRepository dependency
  // if we use an other dependency injection method like field injection, it's a must to use annatation @Autowired
   private final EmployeRepository employeRepository;

   public EmployeServiceImpl(EmployeRepository employeRepository)
   {
       this.employeRepository = employeRepository;

   }


    @Override
    public List<Employe> getAllEmployees() {
        // This method retrieves all employees from the repository
        return employeRepository.findAll();

        // it returns a list of Employe objects or an empty list if no employees are found
    }


    @Override
    public Employe getEmployeeById(Long id) {
        // This method retrieves an employee by ID from the repository
        return employeRepository.findById(id)
                .orElse(null);
    }

    @Transactional // This annotation indicates that the method should be executed within a transaction
    @Override
    public Employe createEmployee(EmployeRequest employeRequest) {
        Employe employe = new Employe();
        employe.setFirstName(employeRequest.getFirstName());
        employe.setLastName(employeRequest.getLastName());
        employe.setEmail(employeRequest.getEmail());

        return employeRepository.save(employe);
    }


    @Transactional
    @Override
    public Employe updateEmployee(Long id, EmployeRequest updatedData) {
        Optional<Employe> optionalEmploye = employeRepository.findById(id); // it's a good practice to check if the employee exists before updating, without this jpa think that it' about inseting a new employee.

        if (optionalEmploye.isPresent()) {
            Employe existingEmploye = optionalEmploye.get();
            // Mettre à jour les champs nécessaires
            existingEmploye.setFirstName(updatedData.getFirstName());
            existingEmploye.setLastName(updatedData.getLastName());
            existingEmploye.setEmail(updatedData.getEmail());
            // other field...
            return employeRepository.save(existingEmploye);

        } else {
            return null;
        }
    }


    @Override
    public void deleteEmployee(Long id) {
        // This method deletes an employee by ID from the repository
        // We can use the deleteById method provided by JpaRepository
       Optional<Employe> optionalEmploye = employeRepository.findById(id);
        if (optionalEmploye.isEmpty()) {
            // If the employee does not exist, you might want to handle it
            // For example, you could throw an exception or log a message
            System.out.println("Employee with ID " + id + " does not exist.");
            return;
        }
        employeRepository.deleteById(id);

    }


}
