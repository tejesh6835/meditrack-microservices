package org.meditrack.authserver.auth_server.controller;

import jakarta.validation.Valid;
import org.meditrack.authserver.auth_server.dto.RegisterRequest;
import org.meditrack.authserver.auth_server.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        service.register(request);
        return ResponseEntity.ok("User registered successfully");
    }
}