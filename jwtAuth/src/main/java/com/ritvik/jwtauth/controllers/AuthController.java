package com.ritvik.jwtauth.controllers;

import com.ritvik.jwtauth.dtos.AuthResponse;
import com.ritvik.jwtauth.dtos.AuthenticationInput;
import com.ritvik.jwtauth.dtos.RegisterInput;
import com.ritvik.jwtauth.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterInput registerInput){
        return ResponseEntity.ok(authenticationService.register(registerInput));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthenticationInput authenticationInput){
        return ResponseEntity.ok(authenticationService.auth(authenticationInput));
    }
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }
}
