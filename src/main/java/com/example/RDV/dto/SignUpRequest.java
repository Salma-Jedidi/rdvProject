package com.example.RDV.dto;

import com.example.RDV.entities.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SignUpRequest {

    private String nom;

    private String email;

    private String password;

    private Role role;

    private LocalDate dateCreation = LocalDate.now();

}
