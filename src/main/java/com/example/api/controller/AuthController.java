package com.example.api.controller;

import com.example.api.InfraSecurity.TokenService;
import com.example.api.dto.LoginRequest;
import com.example.api.dto.RegisterDTO;
import com.example.api.model.User;
import com.example.api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest request) {
        try {
            return authenticateUser(request.getUsername(), request.getPassword());
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

    // Método para autenticar o usuário com o nome de usuário e senha
    private ResponseEntity<?> authenticateUser(String identifier, String password) {
        try {

            User user = userRepository.findByEmailOrPhoneNumber(identifier, identifier)
                    .orElse(null);

            if (user == null) {
                return ResponseEntity.status(401).body("Usuário não encontrado");
            }


            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(identifier, password)
            );


            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Usuário ou Senha Inválidos");
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDTO data) {

        if (userRepository.findByEmailOrPhoneNumber(data.getEmail(), data.getPhoneNumber()).isPresent()) {
            return ResponseEntity.badRequest().body("Email ou número de telefone já está em uso.");
        }

        String encryptedPassword = passwordEncoder.encode(data.getPassword());

        User user = new User();
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setBirthDate(data.getBirthDate());
        user.setEmail(data.getEmail());
        user.setPhoneNumber(data.getPhoneNumber());
        user.setPassword(encryptedPassword);

        userRepository.save(user);

        //String token = tokenService.generateToken(user);

        //return ResponseEntity.ok(Collections.singletonMap("token", token));
        return ResponseEntity.ok("Registrado com sucesso.");
    }
}
