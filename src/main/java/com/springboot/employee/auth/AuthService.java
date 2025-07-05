package com.springboot.employee.auth;

import com.springboot.employee.auth.dto.AuthRequest;
import com.springboot.employee.auth.dto.AuthResponse;
import com.springboot.employee.auth.dto.RegisterRequest;
import com.springboot.employee.config.JwtService;
import com.springboot.employee.entity.User;
import com.springboot.employee.repository.UserRepository;
import com.springboot.employee.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        // Vérifier si un rôle est fourni, sinon assigner USER par défaut
        Role userRole = (request.getRole() != null) ? request.getRole() : Role.USER;

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(userRole)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        // Si on arrive ici, l'utilisateur est authentifié
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(); // Devrait toujours exister si l'authentification a réussi

        var jwtToken = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

}