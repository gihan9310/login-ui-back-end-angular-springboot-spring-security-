package com.gihanz.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {
    private String jwtToken;

    @JsonIgnore()
    public static LoginResponse getJwtResposnse(String token){
        return new LoginResponse(token);
    }
}
