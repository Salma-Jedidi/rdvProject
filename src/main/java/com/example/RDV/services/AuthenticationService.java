package com.example.RDV.services;

import com.example.RDV.dto.JwtAuthenticationResponse;
import com.example.RDV.dto.RefreshTokenRequest;
import com.example.RDV.dto.SignUpRequest;
import com.example.RDV.dto.SigninRequest;
import com.example.RDV.entities.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signin(SigninRequest signinRequest);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
    void sendPasswordByEmail(String email);
}
