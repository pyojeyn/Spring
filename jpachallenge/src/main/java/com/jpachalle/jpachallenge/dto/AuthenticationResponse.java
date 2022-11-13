package com.jpachalle.jpachallenge.dto;

import lombok.Getter;
import org.springframework.security.authentication.AuthenticationManager;

@Getter
public class AuthenticationResponse {
    private final String jwt;

    public AuthenticationResponse(String jwt){
        this.jwt = jwt;
    }

}
