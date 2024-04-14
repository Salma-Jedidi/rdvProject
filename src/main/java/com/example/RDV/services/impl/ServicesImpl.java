package com.example.RDV.services.impl;

import com.example.RDV.entities.*;
import com.example.RDV.repository.*;
import com.example.RDV.services.Services;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServicesImpl implements Services {
    @Autowired
    MedRepository medecinRepository;
    @Autowired
    ServiceRepository serviceRepository;
    @Autowired
    SpecialiteRepository specialiteRepository;

    @Autowired
    rdvRepository rdvRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private EtabRepository etablissementRepository;

    @Autowired
    private DelegRepository delegationRepository;
    @Autowired
    private  GovRepository gouvernoratRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private  DocumentRepository documentRepository;
    @Autowired
    private DossierMedRepository dossierMedicalRepository;
    @Autowired
    private MessageRepository messagesRepository;

//Fonctionnalités de l'Administrateur
    //Gerer les utilisateurs
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Integer idUser) {
        userRepository.deleteById(idUser);
    }

    @Override
    public User affichUser(Integer idUser) {
        return userRepository.findById(idUser).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    public List<Specialite> getAllSpecialites() {
        return specialiteRepository.findAll();
    }
    @Override
    public List<ServiceMed> getAllServiceMedicales() {
        return serviceRepository.findAll();
    }

    @Override
    public List<Medecin> getAllMedecins() {
        return medecinRepository.findAll();
    }

    @Override
    public List<Delegation> getAllDelegatios() {
        return delegationRepository.findAll();
    }

    @Override
    public List<Etablissement> getAllEtablissement() {
        return etablissementRepository.findAll();
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public List<RDV> getAllRDVs() {
        return rdvRepository.findAll();
    }

    @Override
    public Medecin addMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public Medecin updateMedecin(Integer cinMedecin, Medecin updatedMedecin) {

        Medecin existingMedecin = medecinRepository.findByCinMedecin(cinMedecin);

        if (existingMedecin != null) {

            existingMedecin.setCinMedecin(updatedMedecin.getCinMedecin());
            existingMedecin.setNomMedecin(updatedMedecin.getNomMedecin());
            existingMedecin.setEtatMedecin(updatedMedecin.getEtatMedecin());
            existingMedecin.setDelegationMedecin(updatedMedecin.getDelegationMedecin());
            existingMedecin.setServiceMed(updatedMedecin.getServiceMed());
            existingMedecin.setSpecialite(updatedMedecin.getSpecialite());
            existingMedecin.setTel(updatedMedecin.getTel());
            existingMedecin.setNomDeletablissement(updatedMedecin.getNomDeletablissement());
            existingMedecin.setPrixConsultation(updatedMedecin.getPrixConsultation());
            // Update other attributes as needed

            // Save the updated medecin
            existingMedecin = medecinRepository.save(existingMedecin);
        } else {
            // Handle the case where the medecin with the given CIN is not found
            throw new EntityNotFoundException("Medecin not found for CIN: " + cinMedecin);
        }

        return existingMedecin;
    }


    @Override
    public void deleteMedecin(Integer idMedecin) {
        medecinRepository.deleteById(idMedecin);
    }

    @Override
    public Medecin affichMedecin(Integer cinMedecin) {
        return medecinRepository.findByCinMedecin(cinMedecin);
    }

    //Gérer les Services
    @Override
    public void assignSpecialiteAndServiceToMedecin(Integer idSpecialite, Integer idService, Integer idMedecin) {
        Medecin medecin = medecinRepository.findById(idMedecin).orElse(null);

        if (medecin != null) {
            ServiceMed service = serviceRepository.findById(idService).orElse(null);
            Specialite specialite = specialiteRepository.findById(idSpecialite).orElse(null);

            if (service != null) {
                medecin.setServiceMed(service);
                // Set les attributs nécessaires, par exemple :
                medecin.setLibelleService(service.getLibService());

            }

            if (specialite != null) {
                medecin.setSpecialite(specialite);
                // Set les attributs nécessaires, par exemple :
                medecin.setLibelleSpecialite(specialite.getLibSpecialite());
            }

            medecinRepository.save(medecin);
        }
    }
    public void assignPatientAndMedecinTordv(String nomDuPatient, String nomDuMedecin,RDV rdv) {


        if (rdv != null) {
            Medecin med = medecinRepository.findByNomMedecin(nomDuMedecin);
            Patient patient = patientRepository.findByNomPatient(nomDuPatient);

            if (med != null && patient != null) {
                rdv.setMedecin(med);
                rdv.setPatient(patient);
                rdv.setNomDuMedecin(med.getNomMedecin());
                rdv.setNomDuPatient(patient.getNomPatient());
                rdvRepository.save(rdv);
                System.out.println("Association successful.");
            } else {
                System.out.println("Medecin or Patient not found.");
            }
        } else {
            System.out.println("RDV not found.");
        }
    }
    @Override
    public Etablissement addEtablissement(Etablissement etablissement) {
        return etablissementRepository.save(etablissement);
    }

    @Override
    public Etablissement updateEtablissement(Etablissement etablissement) {
        return etablissementRepository.save(etablissement);
    }

    @Override
    public void deleteEtablissement(Integer idEtablissement) {
        etablissementRepository.deleteById(idEtablissement);
    }

    @Override
    public List<Etablissement> affichEtablissement(Integer codeEtablissement) {

        List<Etablissement> etablissements= etablissementRepository.findAllByCodeEtablissement(codeEtablissement);
        return etablissements; }


    @Override
    public void assignEtablissementToMedecin(Integer idEtablissement, Integer idMedecin) {
        Etablissement etablissement = etablissementRepository.findById(idEtablissement).orElse(null);
        Medecin medecinPublic = medecinRepository.findById(idMedecin).orElse(null);

        if (etablissement != null && medecinPublic != null) {

            System.out.println("Association en cours : Etablissement ID - " + idEtablissement +
                    ", Medecin ID - " + idMedecin);

            // Ajouter l'établissement à la liste des établissements du médecin
            medecinPublic.getEtablissements().add(etablissement);
            medecinPublic.setNomDeletablissement(etablissement.getLibEtablissement());

            medecinRepository.save(medecinPublic);

            // Ajouter des logs pour confirmer l'association
            System.out.println("Association réussie : Etablissement ID - " + idEtablissement +
                    ", Medecin ID - " + idMedecin);
        } else {
            // Ajouter des logs pour indiquer si l'une des entités est null
            System.out.println("Etablissement ou Medecin non trouvé.");
        }
    }


    @Override
    public Delegation addDelegation(Delegation delegation) {

        Delegation d= delegationRepository.save(delegation);
        return d;
    }

    @Override
    public Delegation updateDelegation(Delegation delegation) {
        return delegationRepository.save(delegation);}

    @Override
    public void deleteDelegation(Integer codeDelegation) {
        delegationRepository.deleteByCodeDelegation(codeDelegation);
    }

    @Override
    public List<Delegation> affichDelegation(Integer codeDelegation) {
        List<Delegation> delegations = delegationRepository.findAllByCodeDelegation(codeDelegation);

        if (delegations.isEmpty()) {
            // Vous pouvez lever une exception, logger un avertissement ou prendre une autre action selon vos besoins.
            throw new EntityNotFoundException("Aucune délégation trouvée pour le code " + codeDelegation);
        }

        return delegations;
    }

    @Override
    public Gouvernorat addGouvernorat(Gouvernorat gouvernorat) {
        return gouvernoratRepository.save(gouvernorat);
    }

    @Override
    public Gouvernorat updateGouvernorat(Gouvernorat gouvernorat) {
        return gouvernoratRepository.save(gouvernorat);
    }

    @Override
    public void deleteGouvernorat(Integer codeGouvernorat) {
        gouvernoratRepository.deleteByCodeGouvernorat(codeGouvernorat);
    }

    @Override
    public Gouvernorat affichGouvernorat(Integer idGouvernorat) {
        return gouvernoratRepository.findById(idGouvernorat).orElse(null);
    }

    @Override
    public void assignDelegationToGouvernorat(Integer idDelegation, Integer idGouvernorat) {
        Gouvernorat gouvernorat = gouvernoratRepository.findById(idGouvernorat).get();
        Delegation delegation = delegationRepository.findById(idDelegation).get();
            delegation.setGouvernorat(gouvernorat);
            delegation.setCodeDuGouvernorat(gouvernorat.getCodeGouvernorat());
            delegationRepository.save(delegation);

    }

    @Override
    public void assignEtablissementToGouvernorat(Integer codeEtablissement,Integer codeGouvernorat) {
        Etablissement etablissement = etablissementRepository.findByCodeEtablissement(codeEtablissement);
        Gouvernorat gouvernorat = gouvernoratRepository.findByCodeGouvernorat(codeGouvernorat);

        if (etablissement != null && gouvernorat != null) {
            etablissement.setGouvernorat(gouvernorat);
            etablissementRepository.save(etablissement);
        }
    }

    //Fonctionnalités de la Secrétaire
    //Gestion des Rendez-vous
    @Override
    public List<RDV> getRendezVousByPatientAndMedecin(Integer idPatient, Integer idMedecin) {
        Patient patient = patientRepository.findById(idPatient).orElse(null);
        Medecin medecin = medecinRepository.findById(idMedecin).orElse(null);

        if (patient != null && medecin != null) {

            return rdvRepository.findByPatientAndMedecin(patient, medecin);
        } else {

            return null;
        }
    }


    @Override
    public RDV addRDV(RDV rdv) {
        // Retrieve the corresponding Medecin entity based on the name
        Medecin medecin = medecinRepository.findByNomMedecin(rdv.getNomDuMedecin());
        // Retrieve the corresponding Patient entity based on the name
        Patient patient = patientRepository.findByNomPatient(rdv.getNomDuPatient());

        // Set the associated Medecin and Patient entities to the RDV
        rdv.setMedecin(medecin);
        rdv.setPatient(patient);
        rdv.setPaiementRDV(PaiementRDV.NonPayes);
        // Save the RDV to associate it with the Medecin and Patient entities
        return rdvRepository.save(rdv);
    }

    @Override
    public RDV updateRDV(RDV rdv) {
        RDV existingRDV = rdvRepository.findById(rdv.getIdRDV()).orElse(null);
        if (existingRDV != null) {
            Medecin medecin = medecinRepository.findByNomMedecin(rdv.getNomDuMedecin());
            // Retrieve the corresponding Patient entity based on the name
            Patient patient = patientRepository.findByNomPatient(rdv.getNomDuPatient());

            // Set the associated Medecin and Patient entities to the RDV
            rdv.setMedecin(medecin);
            rdv.setPatient(patient);
            existingRDV.setDateRDV(rdv.getDateRDV());
            existingRDV.setHeureRdv(rdv.getHeureRdv());
            existingRDV.setRemarques(rdv.getRemarques());

            // Save the updated RDV
            return rdvRepository.save(existingRDV);
        } else {
            // Handle the case where the RDV to update is not found
            // You can throw an exception or return null, depending on your requirements
            return null;
        }
    }
    @Override
    public void deleteRDV(Integer idRDV) {
        rdvRepository.deleteById(idRDV);
    }
    @Override
     public List<RDV> getAcceptedAppointments() {
            return rdvRepository.getRendezVousByEtat(EtatRDV.ACCEPTER);
        }
    //Enregistrement des Patients

    @Override
    public Document getDocument(Integer patientCin) {
        Patient patient = patientRepository.findByCin(patientCin);
        if (patient != null) {
            // Use the updated method to find a document by patient CIN
            return documentRepository.findByPatient_Cin(patientCin);
        } else {
            throw new EntityNotFoundException("Patient not found for CIN: " + patientCin);
        }
    }

    @Override
    public Document addDocument(MultipartFile file, Integer patientCIN) throws IOException {
        Patient patient = patientRepository.findByCin(patientCIN);
        if (patient != null) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fileType = file.getContentType();
            byte[] data = file.getBytes();

            Document document = new Document(null, fileName, fileType, data, patient);
            return documentRepository.save(document);
        } else {
            throw new EntityNotFoundException("Patient not found for CIN: " + patientCIN);
        }
    }
    @Override
    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }
    @Override
    public Patient updatePatient(Integer cinPatient, Patient updatedPatient) {

        Patient existingPatient = patientRepository.findByCin(cinPatient);

        if (existingPatient != null) {


            existingPatient.setCin(updatedPatient.getCin());
            existingPatient.setNomPatient(updatedPatient.getNomPatient());
            existingPatient.setEmail(updatedPatient.getEmail());
            existingPatient.setDateNaissance(updatedPatient.getDateNaissance());
            existingPatient.setModePaiement(updatedPatient.getModePaiement());
            existingPatient.setTelephone(updatedPatient.getTelephone());
            existingPatient.setTypeCaisse(updatedPatient.getTypeCaisse());
            existingPatient.setNomDelegation(updatedPatient.getNomDelegation());
            // Update other attributes as needed

            // Save the updated patient
            existingPatient = patientRepository.save(existingPatient);
        } else {
            // Handle the case where the patient with the given CIN is not found
            throw new EntityNotFoundException("Patient not found for CIN: " + cinPatient);
        }

        return existingPatient;
    }

    @Override
    public void deletePatient(Integer CIN) {
        Patient patient = patientRepository.findByCin(CIN);
        if (patient != null) {
            // Delete the patient by using the entity's ID
            patientRepository.deleteById(patient.getIdPatient());
        } else {
            // Handle the case when no patient with the given CIN is found
            throw new EntityNotFoundException("Patient not found for CIN: " + CIN);
        }
    }
    @Override
    public List<RDV> getRDVsForPatient(Integer cinPatient) {
    Patient patient = patientRepository.findByCin(cinPatient);

    if (patient == null) {
        throw new EntityNotFoundException("Patient not found for CIN: " + cinPatient);
    }
        List<RDV> rdvsForPatient = rdvRepository.findByPatient_IdPatient(patient.getIdPatient());

        return rdvsForPatient;
    }

    @Override
    public Patient affichPatient(Integer cinPatient) {
        return patientRepository.findByCin(cinPatient);
    }


    //Générer des Rapports
    @Override
    public List<RDV> getAppointmentsBetweenDates(Date startDate, Date endDate) {
        return rdvRepository.getRendezVousBetweenDates(startDate, endDate);
    }
    //Fonctionnalités du Médecin :
    @Override
    public Map<Date, Long> genererRapportFrequentation() {
        List<Object[]> result = rdvRepository.countPatientsByDay();

        Map<Date, Long> rapportFrequentation = new HashMap<>();
        for (Object[] row : result) {
            Date date = (Date) row[0];
            Long nombrePatients = (Long) row[1];
            rapportFrequentation.put(date, nombrePatients);
        }

        return rapportFrequentation;

    }

    //Voir les Rendez-vous :

    @Override
    public List<RDV> getRDVsForMedecin(Integer cinMedecin) {
        Medecin medecin = medecinRepository.findByCinMedecin(cinMedecin);

        if (medecin == null) {
            throw new EntityNotFoundException("Medecin not found for CIN: " + cinMedecin);
        }
        List<RDV> rdvsForMedecin = rdvRepository.findByMedecin_IdMedecin(medecin.getIdMedecin());

        return rdvsForMedecin;
    }



    @Override
    public void marquerEtatRDV(Integer idRDV, EtatRDV nouvelEtat, Integer cinMedecin) {
        RDV rdv = rdvRepository.findById(idRDV).orElse(null);

        if (rdv != null) {
            Medecin medecin = medecinRepository.findByCinMedecin(cinMedecin);

            // Vérifiez si le médecin est associé au RDV
            if (medecin != null && rdv.getMedecin().equals(medecin)) {
                rdv.setEtatRDV(nouvelEtat);
                rdvRepository.save(rdv);
            }
        }
    }
    //Accès au Dossier Médical :
    @Override
    public DossierMedical getDossierMedicalByCin(Integer cinPatient) {
        Patient patient = patientRepository.findByCin(cinPatient);
        if (patient != null && patient.getDossierMed() != null) {
            return patient.getDossierMed();
        } else {
            throw new RuntimeException("Dossier médical pour le patient avec le numéro d'identification (cin) "
                    + cinPatient + " non trouvé.");
        }
    }
    @Override
    public Optional<RDV> findRDVCommun(Integer cinPatient, Integer cinMedecin) {

        Optional<RDV> rdvCommun = rdvRepository.findRDVCommun(cinPatient, cinMedecin);
        return rdvCommun;
    }
    @Override
    public DossierMedical ajouterObservation(Integer cinPatient, String nouvelleObservation) {
        DossierMedical dossier = dossierMedicalRepository.findByCinPatient(cinPatient);

        if (dossier != null) {
            String observationsActuelles = dossier.getObservations();
            if (observationsActuelles == null) {
                dossier.setObservations(nouvelleObservation);
            } else {
                dossier.setObservations(observationsActuelles + "\n" + nouvelleObservation);
            }
            dossierMedicalRepository.save(dossier);
        }
        return dossier;
    }
    //Affichage du salaire :
    //	Compter prixConsultation à une date
    @Override
    public int afficherRevenueDuMedecin(Integer cinMedecin) {

        Medecin medecin = medecinRepository.findByCinMedecin(cinMedecin);

        if (medecin == null) {
            return 0;
        }
        Integer medecinId = medecin.getIdMedecin();

        List<RDV> rdvs = rdvRepository.findByMedecin_IdMedecinAndPaiementRDV(medecinId, PaiementRDV.Payes);


        int sommePrixConsultation = rdvs.stream()
                .mapToInt(rdv -> rdv.getMedecin().getPrixConsultation())
                .sum();

        return sommePrixConsultation;
    }

//Fonctionnalités du Patient :

    @Override
    public void marquerEtatDuRDV(Integer idRDV, EtatRDV nouvelEtat, Integer cinPatient) {
        RDV rdv = rdvRepository.findById(idRDV).orElse(null);

        if (rdv != null) {
            Patient patient = patientRepository.findByCin(cinPatient);

            // Vérifiez si le patient est associé au RDV
            if (patient != null && rdv.getMedecin().equals(patient)) {
                rdv.setEtatRDV(nouvelEtat);
                rdvRepository.save(rdv);
            }
        }

    }
    //Accès au Dossier Médical :

    @Override
    public DossierMedical associerDossierMedicalAuPatient(Integer cinPatient,DossierMedical nouveauDossierMedical) {
        // Récupérer le patient à partir de son cin
        Patient patient = patientRepository.findByCin(cinPatient);
        if (patient == null) {
            throw new RuntimeException("Patient avec le numéro d'identification (cin) " + cinPatient + " non trouvé.");
        }

        // Assigne les attributs du patient au nouveau dossier médical
        nouveauDossierMedical.setCinPatient(patient.getCin());
        nouveauDossierMedical.setNomDuPatient(patient.getNomPatient());
        nouveauDossierMedical.setDateNaissancePatient(patient.getDateNaissance());
        nouveauDossierMedical.setTelephonePatient(patient.getTelephone());

        nouveauDossierMedical.setEtatClinique( nouveauDossierMedical.getEtatClinique());
        nouveauDossierMedical.setGroupe_sanguin( nouveauDossierMedical.getGroupe_sanguin());
        nouveauDossierMedical.setAllergie( nouveauDossierMedical.getAllergie());
        nouveauDossierMedical.setPrescriptions_therapeutiques( nouveauDossierMedical.getPrescriptions_therapeutiques());
        nouveauDossierMedical.setObservations( nouveauDossierMedical.getObservations());
        nouveauDossierMedical.setResultats_examen( nouveauDossierMedical.getResultats_examen());
        // Associer le nouveau dossier médical au patient
        nouveauDossierMedical.setPatient(patient);
        patient.setDossierMed(nouveauDossierMedical);

        dossierMedicalRepository.save(nouveauDossierMedical);

        patientRepository.save(patient);
        return nouveauDossierMedical;
    }


    //choisir mode de paiement

    public void choisirModePaiement(Integer cinPatient, ModePaiement modePaiementChoisi,TypeCaisse typeCaisse) {
        Patient patient = patientRepository.findByCin(cinPatient);

        if (patient != null) {

            patient.setModePaiement(modePaiementChoisi);
            patient.setTypeCaisse(typeCaisse);
            patientRepository.save(patient);
        }
    }

    @Override
    public void marquerEtatPaiementRDV(Integer idRDV,PaiementRDV paiementRDV){
            RDV rdv=rdvRepository.findById(idRDV).orElse(null);
            if(rdv!=null)
            {
                rdv.setPaiementRDV(paiementRDV);
                rdvRepository.save(rdv);
            }
    }
    @Override
    public List<RDV> getRDVPayes() {
        return rdvRepository.findAll().stream()
                .filter(rdv -> rdv.getPaiementRDV() == PaiementRDV.Payes)
                .collect(Collectors.toList());
    }

    @Override
    public List<RDV> getRDVNonPayes() {
        return rdvRepository.findAll().stream()
                .filter(rdv -> rdv.getPaiementRDV() == PaiementRDV.NonPayes)
                .collect(Collectors.toList());
    }
    public List<Medecin> findMedecinsBySpecialite(Integer idSpecialite) {

        Specialite specialite = specialiteRepository.findById(idSpecialite).orElse(null);

        if (specialite != null) {
            return medecinRepository.findBySpecialite(specialite);
        } else {

            return null;
        }
    }
    public List<Medecin> rechercheMedecins(String delegation, String libelleService, String libelleSpecialite) {
        if (delegation == null && libelleService == null && libelleSpecialite == null) {
            return medecinRepository.findAll();
        } else if (delegation != null && libelleService == null && libelleSpecialite == null) {
            return medecinRepository.findByDelegation(delegation);
        } else if (delegation == null && libelleService == null && libelleSpecialite != null) {
            return medecinRepository.findBySpecialite(libelleSpecialite);
        } else {
            if (delegation == null) {
                return medecinRepository.findByServiceAndSpecialite(libelleService, libelleSpecialite);
            } else if (libelleService == null) {
                return medecinRepository.findByDelegationAndSpecialite(delegation, libelleSpecialite);
            } else if (libelleSpecialite == null) {
                return medecinRepository.findByDelegationAndService(delegation, libelleService);
            } else {
                return medecinRepository.findByDelegationAndServiceAndSpecialite(delegation, libelleService, libelleSpecialite);
            }
        }
    }


    /*    public List<Medecin> rechercheMedecins(String delegation, String libelleService, String libelleSpecialite) {
        return medecinRepository.findByDelegationAndServiceAndSpecialite(delegation, libelleService, libelleSpecialite);
    }*/
    //Messages
    @Override
    public MessagePatient sendMessage(Integer cinPatient,String contenue, MessagePatient message) {
        // Recherche du patient par nomPatient
        Patient patient = patientRepository.findByCin(cinPatient);
        if (patient == null) {
            throw new RuntimeException("Patient avec cin Patient'" + cinPatient + "' non trouvé.");
        }

        // Définir les autres informations du message
        message.setNomPatientMessage(patient.getNomPatient());
       message.setEmail(patient.getEmail());
        message.setContenueMessage(contenue);
        message.setDateEnvoieMessage(LocalDate.now());
        message.setPatient(patient);
        MessagePatient savedMessage = messagesRepository.save(message);

        // Ajouter le message à la liste des messages associés au patient
        List<MessagePatient> patientMessages = patient.getMessages();
        patientMessages.add(savedMessage);
        patient.setMessages(patientMessages);
        patientRepository.save(patient);

        // Retourner le message enregistré
        return savedMessage;
    }

/*
    @Override
    public Messages replyMessage(String nomPatient, String reponse) {
        // Recherche du message par le nom du patient
        Messages message = messagesRepository.findByPatientNomPatientMessage(nomPatient);
        if (message == null) {
            // Gérer le cas où le message n'est pas trouvé
            return null;
        }
        // Mise à jour de la réponse
        message.setReponseMessage(reponse);
        message.setDateEnvoiReponse(LocalDate.now());

        // Enregistrement de la réponse dans la base de données
        return messagesRepository.save(message);
    }*/
    @Override
public double pourcentageRDVPayes() {
    // Compter le nombre de rendez-vous payés
    long nbRDVPayes = rdvRepository.countByPaiementRDV(PaiementRDV.Payes);

    // Compter le nombre total de rendez-vous
    long nbTotalRDV = rdvRepository.count();

    // Calculer le pourcentage de rendez-vous payés
    double pourcentage = (double) nbRDVPayes / nbTotalRDV * 100;

    return pourcentage;
}
    @Override
    public double  getNombreRDVByEtat(EtatRDV etatRDV) {
        // Comptez le nombre de rendez-vous dans l'état spécifié
        double  nbRDVParEtat = rdvRepository.countByEtatRDV(etatRDV);

        // Comptez le nombre total de rendez-vous
        double  nbTotalRDVEtat = rdvRepository.count();

        return nbRDVParEtat;
    }
    @Override
    public List<MessagePatient> afficherTousLesMessagesDesPatients() {
        List<MessagePatient> messages = messagesRepository.findAll();
        return messages;
    }
}
