package com.example.RDV.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
public class Gouvernorat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGouvernorat;
    private Integer codeGouvernorat;

    private String libGouvernorat;

    @JsonIgnore
    @OneToMany(mappedBy = "gouvernorat")
    private List<Delegation> delegations;
}