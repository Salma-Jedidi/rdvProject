package com.example.RDV.services.impl;


import com.example.RDV.dto.JwtAuthenticationResponse;
import com.example.RDV.dto.RefreshTokenRequest;
import com.example.RDV.dto.SignUpRequest;
import com.example.RDV.dto.SigninRequest;
import com.example.RDV.entities.User;
import com.example.RDV.repository.UserRepository;
import com.example.RDV.services.AuthenticationService;
import com.example.RDV.services.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public User signup(SignUpRequest signUpRequest){
        User user=new User();

        user.setEmail(signUpRequest.getEmail());
        user.setNom(signUpRequest.getNom());
        user.setRole(signUpRequest.getRole());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setDateCreation(LocalDate.now());
        return userRepository.save(user);

    }

    public JwtAuthenticationResponse signin(SigninRequest signinRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),signinRequest.getPassword()));

        var user = userRepository.findByEmail(signinRequest.getEmail()).orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        var jwt =jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();


        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);
        jwtAuthenticationResponse.setRole(user.getRole().name());

        return jwtAuthenticationResponse;
    }


    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest){
        String userEmail =jwtService.extractUserName(refreshTokenRequest.getToken());
        User user =userRepository.findByEmail(userEmail).orElseThrow();
        if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)){
            var jwt = jwtService.generateToken(user);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();


            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());
            return jwtAuthenticationResponse;
        }
        return null;
    }

    public void sendPasswordByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String password = user.getPassword(); // Récupérer le mot de passe de l'utilisateur
        String emailSubject = "Récupération de mot de passe";
        String emailContent = "Votre mot de passe est : " + password; // Contenu de l'e-mail

        sendEmail(email, emailSubject, emailContent); // Envoyer l'e-mail à l'utilisateur
    }
    private final JavaMailSender javaMailSender;


    private void sendEmail(String recipientEmail, String subject, String content) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientEmail);
        email.setSubject(subject);
        email.setText(content);
        javaMailSender.send(email);
    }
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean isPasswordValid(String password) {
        return password != null && password.length() >= 6;
    }

}
