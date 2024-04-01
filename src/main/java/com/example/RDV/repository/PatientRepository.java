package com.example.RDV.repository;
import com.example.RDV.entities.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    Patient findByCin(Integer cin);

    void deleteByCin(Integer cin);

    Patient findByNomPatient(String nomDuPatient);
}
