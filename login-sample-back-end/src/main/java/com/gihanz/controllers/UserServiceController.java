package com.gihanz.controllers;

import com.gihanz.configs.security.TokenProvider;
import com.gihanz.dtos.LoginResponse;
import com.gihanz.dtos.UserDTO;
import com.gihanz.services.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin()
public class UserServiceController {

    private UserServiceImpl userService;

    private AuthenticationManager authenticationManager;


    private TokenProvider jwtTokenUtil;

    public UserServiceController(UserServiceImpl userService, AuthenticationManager authenticationManager, TokenProvider jwtTokenUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerAccount(@RequestBody UserDTO dto) {
        UserDTO user = userService.registerUserAccount(dto);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserDTO dto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.getUsername(),
                dto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        final String token = jwtTokenUtil.generateToken(authenticate);
        return ResponseEntity.ok(LoginResponse.getJwtResposnse(token));
    }

}
