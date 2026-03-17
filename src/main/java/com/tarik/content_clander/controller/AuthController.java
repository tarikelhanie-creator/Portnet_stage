package com.tarik.content_clander.controller;


import com.tarik.content_clander.DTO.LoginRequestDTO;
import com.tarik.content_clander.DTO.UserDTO;
import com.tarik.content_clander.model.User;
import com.tarik.content_clander.security.JwtUtil;
import com.tarik.content_clander.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO requestDTO){
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDTO.getUsername(),
                            requestDTO.getPassword()
                    )
            );
            String token = jwtUtil.generateToken(requestDTO.getUsername());
            return ResponseEntity.ok(token);
        }catch (AuthenticationException e){
            return  ResponseEntity.status(401).body("Inavlid username or password");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user){
        try{
            UserDTO newuser = userService.createUser(user);
            return ResponseEntity.status(201).body(newuser);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
