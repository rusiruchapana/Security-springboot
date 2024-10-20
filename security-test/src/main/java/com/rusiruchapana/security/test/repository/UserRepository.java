package com.rusiruchapana.security.test.repository;

import com.rusiruchapana.security.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User , Long> {
    Optional<User> findByUserName(String username);
}
