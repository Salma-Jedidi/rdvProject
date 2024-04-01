package com.example.RDV.repository;

import com.example.RDV.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

    Document findByPatient_Cin(Integer patientCin);

}
