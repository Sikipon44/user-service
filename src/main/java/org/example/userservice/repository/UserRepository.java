package org.example.userservice.repository;

import org.example.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByUserName(String userName);
    User findUserByUserNameAndPassword(String email, String password);
}
