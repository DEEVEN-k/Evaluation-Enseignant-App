package com.esi.evaluation.controller;

import com.esi.evaluation.model.Etudiant;
import com.esi.evaluation.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;

    // Obtenir la liste de tous les étudiants
    @GetMapping
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.getAllEtudiants();
    }

    // Obtenir un étudiant par ID
    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable Long id) {
        return etudiantService.getEtudiantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Ajouter un nouvel étudiant
    @PostMapping
    public Etudiant createEtudiant(@RequestBody Etudiant etudiant) {
        return etudiantService.saveEtudiant(etudiant);
    }

    // Mettre à jour un étudiant existant
    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> updateEtudiant(@PathVariable Long id, @RequestBody Etudiant etudiantDetails) {
        return (ResponseEntity<Etudiant>) etudiantService.updateEtudiant(id, etudiantDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Supprimer un étudiant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        boolean deleted = etudiantService.deleteEtudiant(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
