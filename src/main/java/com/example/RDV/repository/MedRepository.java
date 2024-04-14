package com.example.RDV.repository;
import com.example.RDV.entities.Medecin;
import com.example.RDV.entities.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedRepository extends JpaRepository<Medecin, Integer> {
    Medecin findByCinMedecin(Integer cin);
    List<Medecin> findBySpecialite(Specialite specialite);
    @Query("SELECT m FROM Medecin m WHERE m.delegationMedecin = :delegation AND m.libelleService = :libelleService AND m.libelleSpecialite = :libelleSpecialite")
    List<Medecin> findByDelegationAndServiceAndSpecialite(
            @Param("delegation") String delegation,
            @Param("libelleService") String libelleService,
            @Param("libelleSpecialite") String libelleSpecialite
    );

    Medecin findByNomMedecin(String nomDuMedecin);
    @Query("SELECT m FROM Medecin m WHERE  m.libelleService = :libelleService AND m.libelleSpecialite = :libelleSpecialite")

    List<Medecin> findByServiceAndSpecialite(@Param("libelleService") String libelleService, @Param("libelleSpecialite") String libelleSpecialite);
    @Query("SELECT m FROM Medecin m WHERE m.delegationMedecin = :delegation AND m.libelleSpecialite = :libelleSpecialite")

    List<Medecin> findByDelegationAndSpecialite(@Param("delegation") String delegation, @Param("libelleSpecialite") String libelleSpecialite);
    @Query("SELECT m FROM Medecin m WHERE m.delegationMedecin = :delegation AND m.libelleService = :libelleService ")

    List<Medecin> findByDelegationAndService(@Param("delegation") String delegation,@Param("libelleService") String libelleService);
    @Query("SELECT m FROM Medecin m WHERE m.delegationMedecin = :delegation")
    List<Medecin> findByDelegation(@Param("delegation") String delegation);

    @Query("SELECT m FROM Medecin m WHERE m.libelleSpecialite = :libelleSpecialite")
    List<Medecin> findBySpecialite( @Param("libelleSpecialite") String libelleSpecialite);

}
