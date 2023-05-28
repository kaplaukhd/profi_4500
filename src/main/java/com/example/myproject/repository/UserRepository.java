package com.example.myproject.repository;

import com.example.myproject.entity.User;
import com.example.myproject.entity.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

    @Query("SELECT new com.example.myproject.entity.dto.UserDto(" +
            "CONCAT(u.firstName, ' ', u.lastName), " +
            "COUNT(a.id)) " +
            "FROM users u LEFT JOIN u.articles a " +
            "WHERE u.email = :username " +
            "GROUP BY u.id")
    UserDto getUserInfo(String username);
}
