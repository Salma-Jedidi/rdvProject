package com.example.RDV.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data

public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedecin;

    private String nomMedecin;
    private Integer cinMedecin;
    private String tel;
    private Integer prixConsultation;
    private String libelleSpecialite;
    private String libelleService;
    private String nomDeletablissement;
    private String delegationMedecin;
    @JsonIgnore
    @OneToMany(mappedBy = "medecin")
    private List<RDV> rdvs;

    @ManyToOne
    @JoinColumn(name = "specialite_id")
    private Specialite specialite;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private ServiceMed serviceMed;

    @ManyToMany
    @JoinTable(
            name = "medecin_etablissement", // Nom de la table intermédiaire
            joinColumns = @JoinColumn(name = "id_medecin"),
            inverseJoinColumns = @JoinColumn(name = "id_etablissement")
    )
    private List<Etablissement> etablissements;
    @Enumerated
    private EtatMedecin etatMedecin;
}

