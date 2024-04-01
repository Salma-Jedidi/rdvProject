package com.example.RDV.repository;
import com.example.RDV.entities.Etablissement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtabRepository extends JpaRepository<Etablissement, Integer> {
    Etablissement findByCodeEtablissement(Integer codeEtablissement);

    List<Etablissement> findAllByCodeEtablissement(Integer codeEtablissement);
}
