package org.example.userservice.service.impl;

import org.example.userservice.model.User;
import org.example.userservice.repository.UserRepository;
import org.example.userservice.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUserName(username).getFirst();
    }

    @Override
    public User findUser(String username, String password) {
        return userRepository.findUserByUserNameAndPassword(username, password);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUserName(username)
                .stream().map(user -> org.springframework.security.core.userdetails.User.withUsername(user.getUserName())
                        .password(user.getPassword())
                        .roles(user.getRole())
                        .build())
                .findFirst().orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
