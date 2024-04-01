package com.example.RDV.controller;

import com.example.RDV.dto.JwtAuthenticationResponse;
import com.example.RDV.dto.RefreshTokenRequest;
import com.example.RDV.dto.SignUpRequest;
import com.example.RDV.dto.SigninRequest;
import com.example.RDV.entities.User;
import com.example.RDV.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PreAuthorize("permitAll()")
    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpRequest signUpRequest) {
        System.out.println("Received signup request: " + signUpRequest);
        User user = authenticationService.signup(signUpRequest);
        System.out.println("User created: " + user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest signinRequest){
        return ResponseEntity.ok(authenticationService.signin(signinRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        authenticationService.sendPasswordByEmail(email);
        return ResponseEntity.ok("Un email de récupération de mot de passe a été envoyé à votre adresse email.");
    }


}
