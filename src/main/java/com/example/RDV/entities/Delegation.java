package com.example.RDV.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Delegation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDelegation;
    private Integer codeDelegation;

    private Integer codeDuGouvernorat;
    private String libDelegation;
    @ManyToOne
    private Gouvernorat gouvernorat;
}
