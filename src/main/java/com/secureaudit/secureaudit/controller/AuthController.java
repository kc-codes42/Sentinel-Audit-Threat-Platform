package com.secureaudit.secureaudit.controller;

import com.secureaudit.secureaudit.dto.auth.LoginRequest;
import com.secureaudit.secureaudit.dto.auth.LoginResponse;
import com.secureaudit.secureaudit.dto.auth.RegisterRequest;
import com.secureaudit.secureaudit.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok().build();
    }
}
