package com.example.RDV.repository;
import com.example.RDV.entities.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialiteRepository extends JpaRepository<Specialite, Integer> {
    Optional<Object> findByCodeSpecialite(String codeSpecialite);
}
