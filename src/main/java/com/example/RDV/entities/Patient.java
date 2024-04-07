package com.example.RDV.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"cin"})})
public class Patient  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPatient;
    private String nomPatient;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;
    private Integer  cin;
    private Integer telephone;
    private String email;



    @Enumerated(EnumType.STRING)
    private Role role ;

    @Enumerated(EnumType.STRING)
    private TypeCaisse typeCaisse;
    @Enumerated(EnumType.STRING)
    private ModePaiement modePaiement;
    @JsonIgnore
    @OneToMany(mappedBy = "patient")
    private List<RDV> rdvs;

    @OneToOne
    @JoinColumn(name = "piece_jointe_id")
    private Document pieceJointe;

    @OneToOne
    @JoinColumn(name = "dossier_medical_id")
    private Document dossierMedical;
    @OneToOne
    private DossierMedical dossierMed ;

    private String nomDelegation;
    private String password;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateCreation = LocalDate.now();
    @JsonIgnore
    @OneToMany
    private List<Messages> messages;

}
