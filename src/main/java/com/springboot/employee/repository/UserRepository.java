package com.springboot.employee.repository;

import com.springboot.employee.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Méthode pour trouver un utilisateur par son email
    Optional<User> findByEmail(String email);

}