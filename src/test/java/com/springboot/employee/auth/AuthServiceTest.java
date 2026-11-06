package com.springboot.employee.auth;

import com.springboot.employee.auth.dto.RegisterRequest;
import com.springboot.employee.config.JwtService;
import com.springboot.employee.entity.Role;
import com.springboot.employee.entity.User;
import com.springboot.employee.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthServiceTest {
    @Test
    void publicRegistrationCannotGrantAdminRole() {
        UserRepository users = mock(UserRepository.class);
        PasswordEncoder encoder = mock(PasswordEncoder.class);
        JwtService jwt = mock(JwtService.class);
        AuthService service = new AuthService(users, encoder, jwt, mock(AuthenticationManager.class));
        RegisterRequest request = new RegisterRequest();
        request.setFirstName("Example");
        request.setLastName("User");
        request.setEmail("example@example.com");
        request.setPassword("example-password");
        request.setRole(Role.ADMIN);
        when(encoder.encode(any())).thenReturn("encoded");
        when(jwt.generateToken(any(User.class))).thenReturn("example-token");

        service.register(request);

        ArgumentCaptor<User> saved = ArgumentCaptor.forClass(User.class);
        verify(users).save(saved.capture());
        assertEquals(Role.USER, saved.getValue().getRole());
        assertEquals("encoded", saved.getValue().getPassword());
    }
}
