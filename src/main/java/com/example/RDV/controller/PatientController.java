package com.example.RDV.controller;

import com.example.RDV.entities.*;
import com.example.RDV.services.Services;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.nio.file.Path;
@RestController
@RequestMapping("/api/v1/auth/patient")
@RequiredArgsConstructor
public class PatientController {
    private final Services patientServices;
    @PreAuthorize("permitAll()")
    @PostMapping("/{cin}/mode-paiement/type-caisse")
    public ResponseEntity<String> choisirModePaiement(@PathVariable("cin") Integer cinPatient, @RequestParam ModePaiement modePaiementChoisi,@RequestParam TypeCaisse typeCaisse) {
        patientServices.choisirModePaiement(cinPatient, modePaiementChoisi,typeCaisse);
        return ResponseEntity.status(HttpStatus.OK).body("Mode de paiement choisi avec succès pour le patient avec le CIN : " + cinPatient);
    }
    /*@PreAuthorize("permitAll()")
    @PostMapping("/addpatient")
    public Patient addPatient(@RequestPart("patient") Patient patient,
                              @RequestPart(value = "pieceJointe", required = false) MultipartFile pieceJointe,
                              @RequestPart(value = "dossierMedical", required = false) MultipartFile dossierMedical) {
        Patient p = patientServices.addPatient(patient, pieceJointe, dossierMedical);
        return p;
    }

    @PreAuthorize("permitAll()")
    @PutMapping("/updatePatient")
    public Patient updatePatient(@RequestPart("patient") Patient patient,
                                 @RequestPart(value = "pieceJointe", required = false) MultipartFile pieceJointe,
                                 @RequestPart(value = "dossierMedical", required = false) MultipartFile dossierMedical) {
        Patient p = patientServices.updatePatient(patient, pieceJointe, dossierMedical);
        return p;
    }*/
    @PreAuthorize("permitAll()")
    @PostMapping("/addpatient")
    public Patient addPatient(@RequestBody Patient patient  ) {
        Patient p = patientServices.addPatient(patient);
        return p;
    }

    @PreAuthorize("permitAll()")
    @PutMapping("/updatePatien/{cin}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("cin") Integer cin, @RequestBody Patient updatedPatient) {
        // Your update logic here
        Patient updated = patientServices.updatePatient(cin, updatedPatient);
        return ResponseEntity.ok(updated);
    }
    @PreAuthorize("permitAll()")
    @DeleteMapping("/deletePatient/{cin}")
    public void deletePatient(@PathVariable("cin") Integer cin) {
        patientServices.deletePatient(cin);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/affichPatient/{idPatient}")
    public Patient affichPatient(@PathVariable("idPatient") Integer idPatient) {
        Patient p = patientServices.affichPatient(idPatient);
        return p;
    }
    @GetMapping("/medecin/search")
    public List<Medecin> searchMedecin(
            @RequestParam String delegation,
            @RequestParam String libelleService,
            @RequestParam String libelleSpecialite
    ) {
        // Appelez votre service pour effectuer la recherche dans la base de données
        return patientServices.rechercheMedecins(delegation,libelleService,libelleSpecialite);
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/passes/{cin}")
    public ResponseEntity<List<RDV>> getRendezVousPassesPourPatient(@PathVariable("cin") Integer cin) {
        List<RDV> rendezVousPasses = patientServices.getRendezVousPassesPourPatient(cin);
        return ResponseEntity.ok(rendezVousPasses);
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/avenir/{cin}")
    public ResponseEntity<List<RDV>> getRendezVousAVenirPourPatient(@PathVariable("cin") Integer cin) {
        List<RDV> rendezVousAVenir = patientServices.getRendezVousAVenirPourPatient(cin);
        return ResponseEntity.ok(rendezVousAVenir);
    }

    @PostMapping("/addDocument/{patientCIN}")
    public ResponseEntity<String> addDocument(
            @RequestParam("file") MultipartFile file,
            @PathVariable Integer patientCIN
    ) {
        try {
            Document document = patientServices.addDocument(file, patientCIN);
            String filePath = "src/main/resources/static/patientDocuments/" + document.getFileName();
            Files.write(Paths.get(filePath), file.getBytes());

            // Optionally, you can return the document ID or any other relevant information
            return ResponseEntity.status(HttpStatus.CREATED).body("Document created successfully. Document ID: " + document.getId());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Patient not found for CIN: " + patientCIN);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while processing the document");
        }
    }
    @GetMapping("/{patientCIN}")
    public ResponseEntity<org.springframework.core.io.Resource> getDocument(@PathVariable Integer patientCIN) {
        try {
            Document document = patientServices.getDocument(patientCIN);

            if (document != null) {
                byte[] documentContent = document.getContent();
                ByteArrayResource documentResource = new ByteArrayResource(documentContent);

                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(documentResource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/patients/{cinPatient}/rdvs")
    public List<RDV> getRDVsForPatient(@PathVariable Integer cinPatient) {
        return patientServices.getRDVsForPatient(cinPatient);
    }
}
