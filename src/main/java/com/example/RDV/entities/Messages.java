package com.example.RDV.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMessage;
    private String contenue;
    private String reponse;
    @ManyToOne
    @JoinColumn(name = "email")
    private Patient patient;
}
