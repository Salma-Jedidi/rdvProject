package com.example.RDV.controller;

import com.example.RDV.entities.*;
import com.example.RDV.services.Services;
import lombok.AllArgsConstructor;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.List;
import java.sql.Date;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth/adminpage")
public class AdminController {
    private final Services services;
    //Gerer les utilisateurs
    @PreAuthorize("permitAll()")
    @PostMapping("/adduser")
    public User addUser(@RequestBody User user) {
        User u=services.addUser(user);
        return u;
    }

    @PreAuthorize("permitAll()")
    @PutMapping("/updateUser")
    public User updateUser(@RequestBody  User user) {
        User u=services.updateUser(user);
        return u;
    }

    @PreAuthorize("permitAll()")
    @DeleteMapping("/deleteUser/{idUser}")
    public void deleteUser(@PathVariable ("idUser")Integer idUser) {
       services.deleteUser(idUser);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/affichUser/{idUser}")
    public User affichUser(@PathVariable ("idUser")Integer idUser) {
       User u=services.affichUser(idUser);
        return u;
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/listUsers")
    public List<User> getAllUsers() {
        List<User> users=services.getAllUsers();
        return users;
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/listSpecialites")
    public List<Specialite> getAllSpecialites() {
        List<Specialite> specialites=services.getAllSpecialites();
        return specialites;
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/listServiceMed")
    public List<ServiceMed> getAllServiceMedicales() {
        List<ServiceMed> serviceMeds=services.getAllServiceMedicales();
        return serviceMeds;
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/listMedecins")
    public List<Medecin> getAllMedecins() {
       List<Medecin> medecins=services.getAllMedecins();
        return medecins;
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/listDelegations")
    public List<Delegation> getAllDelegatios() {
        List<Delegation> delegations=services.getAllDelegatios();
        return delegations;
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/listEtablissements")
    public List<Etablissement> getAllEtablissement() {
        List<Etablissement> etablissements=services.getAllEtablissement();
        return etablissements;
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/listPatients")
    public List<Patient> getAllPatients() {
        List<Patient> patients=services.getAllPatients();
        return patients;
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/listRDVs")
    public List<RDV> getAllRDVs() {
        List<RDV> rdvs=services.getAllRDVs();
        return rdvs;
    }
    @PreAuthorize("permitAll()")
    @PostMapping("/addmedecin")
    public Medecin addMedecin(@RequestBody Medecin medecin) {
        Medecin u=services.addMedecin(medecin);
        return u;
    }

    @PreAuthorize("permitAll()")
    @PutMapping("/updateMedecin/{cin}")
    public  ResponseEntity<Medecin> updateMedecin(@PathVariable("cin") Integer cin,@RequestBody Medecin updatedmedecin) {
        Medecin m=services.updateMedecin(cin,updatedmedecin);
        return ResponseEntity.ok(m);
    }


    @PreAuthorize("permitAll()")
    @DeleteMapping("/deleteMedecin/{idMedecin}")
    public void deleteMedecin(@PathVariable ("idMedecin")Integer idMedecin) {
        services.deleteMedecin(idMedecin);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/affichMedecin/{cinMedecin}")
    public Medecin affichMedecin(@PathVariable ("cinMedecin")Integer cinMedecin) {
        Medecin m=services.affichMedecin(cinMedecin);
        return m;
    }

    //Gérer les Services
    @PreAuthorize("permitAll()")
    @PutMapping("/assignSpecialiteAndServiceToMedecin/{idSpecialite}/{idService}/{idMedecin}")
    public void assignSpecialiteAndServiceToMedecin(
            @PathVariable("idSpecialite") Integer idSpecialite,
            @PathVariable("idService") Integer idService,
            @PathVariable("idMedecin") Integer idMedecin) {
        services.assignSpecialiteAndServiceToMedecin(idSpecialite, idService, idMedecin);
    }
    @PreAuthorize("permitAll()")
    @PutMapping("/assignPatientAndMedecinTordv/{nomDuPatient}/{nomDuMedecin}")
    public void assignPatientAndMedecinTordv(
            @PathVariable("nomDuPatient") String nomDuPatient,
            @PathVariable("nomDuMedecin") String nomDuMedecin,
            @RequestBody RDV rdv) {
        services.assignPatientAndMedecinTordv(nomDuPatient, nomDuMedecin, rdv);
    }



    @PreAuthorize("permitAll()")
    @PostMapping("/addetablissement")
    public Etablissement addEtablissement(@RequestBody Etablissement etablissement) {
        Etablissement u=services.addEtablissement(etablissement);
        return u;
    }

    @PreAuthorize("permitAll()")
    @PutMapping("/updateEtablissement")
    public Etablissement updateEtablissement(@RequestBody Etablissement etablissement) {
        Etablissement e=services.updateEtablissement(etablissement);
        return e;
    }

    @PreAuthorize("permitAll()")
    @DeleteMapping("/deleteEtablissement/{idEtablissement}")
    public void deleteEtablissement(@PathVariable ("idEtablissement")Integer idEtablissement) {
        services.deleteEtablissement(idEtablissement);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/affichEtablissement/{codeEtablissement}")
    public List<Etablissement> affichEtablissement(@PathVariable ("codeEtablissement") Integer codeEtablissement) {
        List<Etablissement> e=services.affichEtablissement(codeEtablissement);
        return e;
    }

    @PreAuthorize("permitAll()")
    @PutMapping("/assignEtablissementToMedecin/{idEtablissement}/{idMedecin}")
    public void assignEtablissementToMedecin(@PathVariable Integer idEtablissement,@PathVariable Integer idMedecin) {
        services.assignEtablissementToMedecin(idEtablissement,idMedecin);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/adddelegation")
    public Delegation addDelegation(@RequestBody Delegation delegation) {
        Delegation u=services.addDelegation(delegation);
        return u;
    }

    @PreAuthorize("permitAll()")
    @PutMapping("/updateDelegation")
    public Delegation updateDelegation(@RequestBody Delegation delegation) {
        Delegation d=services.updateDelegation(delegation);
        return d;}

    @PreAuthorize("permitAll()")
    @DeleteMapping("/deleteDelegation/{idDelegation}")
    public void deleteDelegation(@PathVariable ("idDelegation")Integer codeDelegation) {
        services.deleteDelegation(codeDelegation);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/affichDelegation/{idDelegation}")
    public List<Delegation> affichDelegation(@PathVariable ("idDelegation")Integer codeDelegation) {
        List<Delegation> delegations = services.affichDelegation(codeDelegation);
        return delegations;
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/addgouvernorat")
    public Gouvernorat addGouvernorat(@RequestBody Gouvernorat gouvernorat) {
        Gouvernorat u=services.addGouvernorat(gouvernorat);
        return u;
    }

    @PreAuthorize("permitAll()")
    @PutMapping("/updateGouvernorat")
    public Gouvernorat updateGouvernorat(@RequestBody Gouvernorat gouvernorat) {
        Gouvernorat g=services.updateGouvernorat(gouvernorat);
        return g;
    }

    @PreAuthorize("permitAll()")
    @DeleteMapping("/deleteGouvernorat/{idGouvernorat}")
    public void deleteGouvernorat(@PathVariable ("idGouvernorat")Integer codeGouvernorat) {
        services.deleteGouvernorat(codeGouvernorat);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/affichGouvernorat/{idGouvernorat}")
    public Gouvernorat affichGouvernorat(@PathVariable ("idGouvernorat") Integer idGouvernorat) {
        Gouvernorat g=services.affichGouvernorat(idGouvernorat);
        return g;
    }

    @PreAuthorize("permitAll()")
    @PutMapping("/assignDelegationToGouvernorat/{idDelegation}/{idGouvernorat}")
    public void assignDelegationToGouvernorat(@PathVariable("idDelegation") Integer idDelegation,@PathVariable("idGouvernorat") Integer idGouvernorat) {
   services.assignDelegationToGouvernorat(idDelegation,idGouvernorat);
    }

    @PreAuthorize("permitAll()")
    @PutMapping("/assignEtablissementToGouvernorat/{codeEtablissement}/{codeGouvernorat}")
    public void assignEtablissementToGouvernorat(@PathVariable Integer codeEtablissement,@PathVariable  Integer codeGouvernorat) {
        services.assignEtablissementToGouvernorat(codeEtablissement,codeGouvernorat);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/addrdv")
    public RDV ajouterRDV(@RequestBody RDV rdv) {
        RDV rendezvous=services.addRDV(rdv);
        return rendezvous;
    }
    @GetMapping("/checkAvailability")
    public boolean checkAvailability(
            @RequestParam String nomDuMedecin,
            @RequestParam String dateRDV,
            @RequestParam String heureRDV
    ) {
        LocalDate parsedDateRDV = LocalDate.parse(dateRDV);
        LocalDate sqlDateRDV = Date.valueOf(parsedDateRDV).toLocalDate(); // Conversion en java.sql.Date

        LocalTime parsedHeureRDV = LocalTime.parse(heureRDV);

        // Appeler le service pour vérifier la disponibilité
        return services.isRDVAvailable(nomDuMedecin, sqlDateRDV, parsedHeureRDV);
    }
    @GetMapping("/suggestTimes")
    public List<LocalTime> suggestAvailableTimes(
            @RequestParam String nomDuMedecin,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateRDV,
            @RequestParam int numberOfSuggestions
    ) {
        // Convertir java.sql.Date en java.time.LocalDate si nécessaire
        LocalDate localDateRDV = dateRDV;

        // Appeler le service avec les paramètres modifiés
        return services.suggestAvailableTimes(nomDuMedecin, localDateRDV, numberOfSuggestions);
    }
    @PreAuthorize("permitAll()")
    @PutMapping("/updaterdv")
    public RDV modifierRDV(@RequestBody RDV rdv) {
        RDV rendezvous=services.updateRDV(rdv);
        return rendezvous;
    }
    @PreAuthorize("permitAll()")
    @DeleteMapping("/deleteRDV/{idRDV}")
    public void supprimerRDV(@PathVariable ("idRDV")Integer idRDV) {
        services.deleteRDV(idRDV);
    }
}
