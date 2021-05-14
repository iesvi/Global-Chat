package com.iesvi.samuel.authbackend.application.service;

import com.iesvi.samuel.authbackend.domain.exception.EmailAlreadyExistsException;
import com.iesvi.samuel.authbackend.domain.exception.UsernameAlreadyExistsException;
import com.iesvi.samuel.authbackend.domain.model.Role;
import com.iesvi.samuel.authbackend.domain.repository.UserRepository;
import com.iesvi.samuel.authbackend.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;


    public String loginUser(String username, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        return tokenProvider.generateToken(authentication);
    }

    public User registerUser(User user, Role role) {
        log.info("Registrando al usuario {}", user.getUsername());

        if (userRepository.existsByUsername(user.getUsername())) {
            log.warn("El usuario {} ya existe.", user.getUsername());

            throw new UsernameAlreadyExistsException(
                    String.format("El usuario %s ya existe.", user.getUsername()));
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            log.warn("El E-Mail {} ya existe.", user.getEmail());

            throw new EmailAlreadyExistsException(
                    String.format("El E-mail %s ya existe.", user.getEmail()));
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>() {{
            add(role);
        }});

        return userRepository.save(user);
    }

    public List<User> findAll() {
        log.info("Recuperando todos los usuarios.");
        return userRepository.findAll();
    }

    public Optional<User> findByUsername(String username) {
        log.info("Recuperando usuario {}", username);
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(String id) {
        log.info("Recuperando usuario {}", id);
        return userRepository.findById(id);
    }
}
