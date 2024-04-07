package com.example.RDV.repository;
import com.example.RDV.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface rdvRepository extends JpaRepository<RDV, Integer> {
    @Query("SELECT r FROM RDV r WHERE r.etatRDV = :etatRDV")
    List<RDV> getRendezVousByEtat(@Param("etatRDV") EtatRDV etatRDV);

    @Query("SELECT r FROM RDV r WHERE r.dateRDV BETWEEN :startDate AND :endDate")
    List<RDV> getRendezVousBetweenDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("SELECT r.dateRDV, COUNT(r) FROM RDV r GROUP BY r.dateRDV")
    List<Object[]> countPatientsByDay();

    @Query("SELECT r FROM RDV r WHERE r.medecin = :medecin AND r.dateRDV > :currentDate")
    List<RDV> findRendezVousAVenirPourMedecin(@Param("medecin") Medecin medecin, @Param("currentDate") Date currentDate);

    @Query("SELECT r FROM RDV r WHERE r.medecin = :medecin AND r.dateRDV < :currentDate")
    List<RDV> findRendezVousPassesPourMedecin(@Param("medecin") Medecin medecin, @Param("currentDate") Date currentDate);

    @Query("SELECT r FROM RDV r WHERE r.patient.cin = :cinPatient AND r.medecin.cinMedecin = :cinMedecin")
    Optional<RDV> findRDVCommun(@Param("cinPatient") Integer cinPatient, @Param("cinMedecin") Integer cinMedecin);

    List<RDV> findByMedecin_CinMedecinAndEtatRDV(Integer cinMedecin, EtatRDV etatRDV);

    @Query("SELECT r FROM RDV r WHERE r.patient.idPatient = :patientId AND r.dateRDV > :currentDate")
    List<RDV> findRendezVousAVenirPourPatient(@Param("patientId") Integer patientId, @Param("currentDate") Date currentDate);

    @Query("SELECT r FROM RDV r WHERE r.patient.idPatient = :patientId AND r.dateRDV < :currentDate")
    List<RDV> findRendezVousPassesPourPatientById(@Param("patientId") Integer patientId, @Param("currentDate") Date currentDate);

    @Query("SELECT r FROM RDV r WHERE r.patient = :patient AND r.medecin = :medecin")
    List<RDV> findByPatientAndMedecin(Patient patient, Medecin medecin);

    List<RDV> findByPatient_IdPatient(Integer patientId);

    List<RDV> findByMedecin_IdMedecin(Integer medecinId);

    List<RDV> findByMedecin_IdMedecinAndEtatRDV(Integer medecinId, EtatRDV accepter);

    List<RDV> findByMedecin_IdMedecinAndPaiementRDV(Integer medecinId, PaiementRDV payes);

    long countByPaiementRDV(PaiementRDV paiementRDV);
}
