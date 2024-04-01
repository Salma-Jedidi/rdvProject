package com.example.RDV.controller;

import com.example.RDV.entities.EtatRDV;
import com.example.RDV.entities.RDV;
import com.example.RDV.services.Services;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth/medecin")
@RequiredArgsConstructor
public class MedecinController {
    private final Services services;
    @PreAuthorize("permitAll()")
    @GetMapping("/medecins/{cinMedecin}/revenue")
    public int afficherRevenueDuMedecin(@PathVariable Integer cinMedecin) {
        return services.afficherRevenueDuMedecin(cinMedecin);
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/medecins/{cinMedecin}/rdvs")
    public List<RDV> getRDVsForMedecin(@PathVariable Integer cinMedecin) {
        return services.getRDVsForMedecin(cinMedecin);
    }
    @PutMapping("/rdvs/{idRDV}/etat/{etat}")
    public ResponseEntity<?> marquerEtatRDV(@PathVariable("idRDV") Integer idRDV,
                                            @PathVariable("etat") EtatRDV etat,
                                            @RequestParam Integer cinMedecin) {
        services.marquerEtatRDV(idRDV, etat, cinMedecin);
        return ResponseEntity.ok().build();
    }

}
