package com.springboot.employee.entity;

import com.springboot.employee.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user") // "user" est un mot-clé réservé dans beaucoup de SGBD
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING) // Stocke l'enum en tant que String (ex: "ADMIN")
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retourne une liste contenant le rôle de l'utilisateur
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        // Notre "username" est l'email
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    // Pour la simplicité, nous retournons true.
    // En production, vous pourriez avoir une logique pour désactiver des comptes.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}