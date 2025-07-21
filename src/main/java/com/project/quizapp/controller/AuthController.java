package com.project.quizapp.controller;

import com.project.quizapp.dao.UserDao;
import com.project.quizapp.model.User;
import com.project.quizapp.response.ResponseHandler;
import com.project.quizapp.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    UserDao userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        User newUser = userRepository.save(user);
//        return ResponseEntity.ok("User registered");
        return ResponseHandler.generate("User data fetched", HttpStatus.OK, newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );

        User dbUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow();

        String token = jwtService.generateToken(
                new org.springframework.security.core.userdetails.User(
                        dbUser.getUsername(),
                        dbUser.getPassword(),
                        List.of(new SimpleGrantedAuthority(dbUser.getRole()))
                )
        );
        return ResponseHandler.generate("Login successful", HttpStatus.OK, token);
    }
}
