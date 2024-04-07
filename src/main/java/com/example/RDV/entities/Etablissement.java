package com.example.RDV.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Etablissement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEtablissement;
    private Integer codeEtablissement;

    private String libEtablissement;
    @ManyToOne
    private Gouvernorat gouvernorat;
    @ManyToMany
    private List<Medecin> medecins;

}
