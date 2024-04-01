package com.example.RDV.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Specialite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSpecialite;
    private String codeSpecialite;
    private String libSpecialite;
    @JsonIgnore
    @OneToMany(mappedBy = "specialite")
    private List<Medecin> medecins;
}
