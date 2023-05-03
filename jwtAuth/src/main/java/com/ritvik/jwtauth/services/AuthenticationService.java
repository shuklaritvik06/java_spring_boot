package com.ritvik.jwtauth.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ritvik.jwtauth.dtos.AuthResponse;
import com.ritvik.jwtauth.dtos.AuthenticationInput;
import com.ritvik.jwtauth.dtos.RegisterInput;
import com.ritvik.jwtauth.entities.Role;
import com.ritvik.jwtauth.entities.User;
import com.ritvik.jwtauth.repositories.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthResponse register(RegisterInput registerInput) {
        var user = User.builder().firstname(registerInput.getFirstname()).lastname(registerInput.getLastname()).email(registerInput.getEmail()).password(passwordEncoder.encode(registerInput.getPassword())).role(Role.USER).build();
        userRepository.save(user);
        var token = jwtService.generateJWT(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthResponse auth(AuthenticationInput authenticationInput) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationInput.getEmail(),authenticationInput.getPassword()));
        var user = userRepository.findByEmail(authenticationInput.getEmail()).orElseThrow();
        var token = jwtService.generateJWT(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return AuthResponse.builder()
                .accessToken(token)
                .refreshToken(refreshToken)
                .build();
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateJWT(user);
                var authResponse = AuthResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
