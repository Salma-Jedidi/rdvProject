package com.example.RDV.dto;

import lombok.Data;

@Data
public class JwtAuthenticationResponse {
           private String token ;
           private String refreshToken;
    private String role;

}
