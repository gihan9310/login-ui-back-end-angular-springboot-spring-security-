package com.gihanz.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gihanz.dtos.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;

    @JsonIgnore()
    public UserDTO getDto(){
        UserDTO user = new UserDTO();
        BeanUtils.copyProperties(this,user);
        user.setPassword("not specify.");
        return user;
    }
}
