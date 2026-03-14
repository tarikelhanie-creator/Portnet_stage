package com.tarik.content_clander.services;


import com.tarik.content_clander.DTO.UserDTO;
import com.tarik.content_clander.model.User;
import com.tarik.content_clander.exeptions.ResourceNotFoundException;
import com.tarik.content_clander.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.tarik.content_clander.model.Role;
import com.tarik.content_clander.repository.RoleRepository;
import java.util.List;
import java.util.stream.Collectors;

import java.util.List;

@Service
public class UserService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public UserDTO toDTO(User user){
        return new UserDTO(
                user.getId_U(),
                user.getUsername(),
                user.getEmail()
        );
    }

    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(this::toDTO).toList();
    }

    public UserDTO getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found with id "+id));
        return toDTO(user);
    }

    public UserDTO createUser(User user){
        if (userRepository.existsByUsername(user.getUsername())){
            throw new RuntimeException("Username is taken");
        }
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already exists");
        }
        List<Role> fullRoles = user.getRoles().stream()
                .map(role -> roleRepository.findById(role.getId_R())
                        .orElseThrow(() -> new ResourceNotFoundException("Role not found with id " + role.getId_R())))
                .collect(Collectors.toList());

        user.setRoles(fullRoles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return toDTO(userRepository.save(user));
    }

    public UserDTO editUserByid(User user, Long id){
        if (!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User not found with id "+ id);
        }
        user.setId_U(id);
        return toDTO(userRepository.save(user));
    }

    public void deleteUser(Long id){
        if (!userRepository.existsById(id)){
            throw new ResourceNotFoundException("User not found with id "+ id);
        }
        userRepository.deleteById(id);
    }

}
