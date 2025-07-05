
                       // DAO

package com.springboot.employee.repository;

import com.springboot.employee.entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // This annotation indicates that this interface is a Spring Data repository
public interface EmployeRepository extends JpaRepository<Employe,Long> {

    // This interface will automatically provide CRUD operations for the Employe entity
    // No additional methods are needed unless custom queries are required

    // JPARepository is an interface that extends PagingAndSortingRepository and provides JPA related method

    // we can add custom query methods here if needed and spring will automatically implement them :

    // Example: List<Employe> findByLastName(String lastName);

}
