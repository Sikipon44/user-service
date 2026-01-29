package org.example.userservice.service;

import org.example.userservice.model.User;

import java.util.List;

public interface IUserService {
    User getUserByEmail(String email);
    List<User> getAllUsers();
    User addUser(User user);
    User getUserByUsername(String username);
    User findUser(String username, String password);
}
