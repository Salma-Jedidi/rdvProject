package com.example.RDV.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class DossierMedical {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDossier;
    private String etatClinique;
    private String groupe_sanguin;
    private String allergie;
    private String prescriptions_therapeutiques;
    private String  resultats_examen;
    private String observations;
    private String nomDuPatient;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissancePatient;
    private Integer  cinPatient;
    private Integer telephonePatient;
    @JsonIgnore
    @OneToOne
    private Patient patient;

}
