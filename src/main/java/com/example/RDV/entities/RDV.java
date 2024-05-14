package com.example.RDV.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
@Entity
@Data
public class RDV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRDV;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateRDV;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "HH:mm")

    private LocalTime heureRdv;
    private String Remarques;
    private String nomDuPatient;
    private String nomDuMedecin;
private String nomDelegation;
    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @Enumerated
    private EtatRDV etatRDV;
    @Enumerated(EnumType.STRING)
    private  PaiementRDV paiementRDV;
}
