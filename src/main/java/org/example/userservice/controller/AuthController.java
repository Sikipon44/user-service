package org.example.userservice.controller;

import org.example.userservice.model.AuthRequest;
import org.example.userservice.model.AuthResponse;
import org.example.userservice.model.User;
import org.example.userservice.service.IUserService;
import org.example.userservice.service.impl.UserServiceImpl;
import org.example.userservice.utilities.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final IUserService userService;

    public AuthController(AuthenticationManager authManager, JwtService jwtService, IUserService userService) {
        this.authManager = authManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password())
        );
        User user = userService.getUserByUsername(request.username());
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
