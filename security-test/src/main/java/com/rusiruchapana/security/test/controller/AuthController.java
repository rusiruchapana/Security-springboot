package com.rusiruchapana.security.test.controller;

import com.rusiruchapana.security.test.dto.request.UserDTO;
import com.rusiruchapana.security.test.entity.Role;
import com.rusiruchapana.security.test.entity.User;
import com.rusiruchapana.security.test.repository.RoleRepository;
import com.rusiruchapana.security.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDTO userDTO){

        if (userRepository.findByUserName(userDTO.getUsername()).isPresent()){
            return "User already exist's in the DB.";
        }else {

            User user = new User();
            user.setUserName(userDTO.getUsername());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

            Set<Role> roles = new HashSet<>();
            Role userRole = roleRepository.findByName("ROLE_USER").orElseThrow(()-> new RuntimeException("Role not found."));
            roles.add(userRole);
            user.setRoles(roles);

            userRepository.save(user);

            return "Succesfully registered user.";
        }
    }

    @PostMapping("/login")
    public String loginUser(){
        return "Login successful!";
    }
}
