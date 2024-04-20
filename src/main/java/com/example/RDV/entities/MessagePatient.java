package com.example.RDV.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class MessagePatient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMessage;
    private String nomPatientMessage;
    private String email;
    private String contenueMessage;
    private String reponseMessage;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEnvoieMessage = LocalDate.now();
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEnvoiReponse ;
    private String nomRepondMessage;
    @ManyToOne
    @JoinColumn(name = "idPatient")
    private Patient patient;
}
