package com.gihanz.services;


import com.gihanz.dtos.UserDTO;
import com.gihanz.entities.User;
import com.gihanz.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService {


    private UserRepository userRepository;

    private BCryptPasswordEncoder bcryptEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.bcryptEncoder = bcryptEncoder;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), new ArrayList<>());
    }

    @Transactional()
    public UserDTO registerUserAccount(UserDTO userDTO){
        try {
            User entity = userDTO.getEntity();
            entity.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
            User user = this.userRepository.save(entity);
            return user.getDto();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
