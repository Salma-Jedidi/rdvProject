package com.example.RDV.services;

import com.example.RDV.entities.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface Services {

//Fonctionnalités de l'Administrateur
    //Gerer les utilisateurs
    public User addUser(User user);
    public User updateUser(User user);
    public void deleteUser(Integer idUser);
    public User affichUser(Integer idUser);
    public List<User> getAllUsers();
    public List<Medecin> getAllMedecins();
    public List<Delegation> getAllDelegatios();
    public List<Etablissement> getAllEtablissement();
    public List<Patient> getAllPatients();
    public List<RDV> getAllRDVs();
    public List<Specialite> getAllSpecialites();
    public List<ServiceMed> getAllServiceMedicales();
    //Enregistrer les medecins
    public Medecin addMedecin(Medecin medecin);
    public Medecin updateMedecin(Integer cinMedecin, Medecin updatedMedecin);
    public void deleteMedecin(Integer idMedecin);
    public Medecin affichMedecin(Integer cinMedecin);
    public void assignPatientAndMedecinTordv(String nomDuPatient, String nomDuMedecin,RDV rdv);


    //Gérer les Services
    public void assignSpecialiteAndServiceToMedecin(Integer idSpecialite, Integer idService, Integer idMedecin);
    //Gérer les Etablissements
    public Etablissement addEtablissement(Etablissement etablissement);
    public Etablissement updateEtablissement(Etablissement etablissement);
    public void deleteEtablissement(Integer idEtablissement);
    public List<Etablissement> affichEtablissement(Integer codeEtablissement);

    public void assignEtablissementToMedecin(Integer idEtablissement, Integer idMedecin);
    //Gerer delegations et gouvernorats
    // Enregistrer les délégations
    public Delegation addDelegation(Delegation delegation);
    public Delegation updateDelegation(Delegation delegation);
    public void deleteDelegation(Integer codeDelegation);
    public List<Delegation> affichDelegation(Integer codeDelegation);

    // Enregistrer les gouvernorats
    public Gouvernorat addGouvernorat(Gouvernorat gouvernorat);
    public Gouvernorat updateGouvernorat(Gouvernorat gouvernorat);
    public void deleteGouvernorat(Integer codeGouvernorat);
    public Gouvernorat affichGouvernorat(Integer idGouvernorat);

    public void assignDelegationToGouvernorat(Integer idDelegation, Integer idGouvernorat);
    public void assignEtablissementToGouvernorat(Integer codeEtablissement, Integer codeGouvernorat);

    //Fonctionnalités de la Secrétaire
    //Gestion des Rendez-vous
    public List<RDV> getRendezVousByPatientAndMedecin(Integer idPatient, Integer idMedecin);
    public RDV addRDV(RDV rdv);
    public RDV updateRDV(RDV rdv);
    public void deleteRDV(Integer idRDV);
    public List<RDV> getAcceptedAppointments();
    //Enregistrement des Patients
    public Patient addPatient(Patient patient);
    public Patient updatePatient(Integer cinPatient, Patient updatedPatient);
    public void deletePatient(Integer CIN);
    public Patient affichPatient(Integer cinPatient);

    //rapport rdv
    public List<RDV> getAppointmentsBetweenDates(Date startDate, Date endDate);
    public Map<Date, Long> genererRapportFrequentation();

 //Fonctionnalités du Médecin :
    //Voir les Rendez-vous :
        public List<RDV> getRendezVousPassesPourMedecin(Integer idMedecin);
        public List<RDV> getRendezVousAVenirPourMedecin(Integer idMedecin);
        public void marquerEtatRDV(Integer idRDV, EtatRDV nouvelEtat, Integer cinMedecin);
    public List<RDV> getRDVsForMedecin(Integer cinMedecin);

    //Accès au Dossier Médical :
/*    public Optional<DossierMedical> afficherDossierMedical(Integer cinPatient, Integer cinMedecin);
    public boolean modifierDossierMedical(Integer cinPatient, Integer cinMedecin, DossierMedical nouveauDossierMedical);
*/
    public Optional<RDV> findRDVCommun(Integer cinPatient, Integer cinMedecin);
    //affichage du salaire
    public int afficherRevenueDuMedecin(Integer cinMedecin);


//Fonctionnalités du Patient :
    //Afficher les rdvs:
    public List<RDV> getRendezVousPassesPourPatient(Integer cin);
    public List<RDV> getRendezVousAVenirPourPatient(Integer cin);
    public void marquerEtatDuRDV(Integer idRDV, EtatRDV nouvelEtat, Integer cinPatient);
    public List<RDV> getRDVsForPatient(Integer cinPatient);
    //Accès au Dossier Médical :
   /* public Optional<DossierMedical> afficherDossierMedical(Integer cinPatient);*/
    //Effectuer des Paiements :
    public void choisirModePaiement(Integer cinPatient, ModePaiement modePaiementChoisi,TypeCaisse typeCaisse);

    public List<Medecin> findMedecinsBySpecialite(Integer idSpecialite);

    public List<Medecin> rechercheMedecins(String delegation, String libelleService, String libelleSpecialite);


    public Document getDocument(Integer id);
    public Document addDocument(MultipartFile file,Integer patientCIN) throws IOException;


}
