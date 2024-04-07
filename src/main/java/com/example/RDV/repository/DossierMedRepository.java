package com.example.RDV.repository;

import com.example.RDV.entities.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DossierMedRepository extends JpaRepository<DossierMedical, Integer> {
    DossierMedical  findByCinPatient(Integer cinPatient);
}
