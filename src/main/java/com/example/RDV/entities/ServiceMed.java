package com.example.RDV.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class ServiceMed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idService;
    private String codeService;
    private String libService;
    @JsonIgnore
    @OneToMany(mappedBy = "serviceMed")
    private List<Medecin> medecins;
}
