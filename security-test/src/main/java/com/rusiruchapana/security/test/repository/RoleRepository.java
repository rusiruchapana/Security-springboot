package com.rusiruchapana.security.test.repository;

import com.rusiruchapana.security.test.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role , Long> {
    Optional<Role> findByName(String name);
}
