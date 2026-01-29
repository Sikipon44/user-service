package org.example.userservice.controller;

import org.example.userservice.model.User;
import org.example.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(IUserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok(new String("Hello from User Service!"));
    }

    @GetMapping("/findUserByEmail/{email}")
    public ResponseEntity<User> findUserByEmail(
            @PathVariable String email
    ) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(
            @RequestBody User user
    ) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userService.addUser(user));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
