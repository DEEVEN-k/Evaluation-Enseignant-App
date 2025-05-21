package com.esi.evaluation.controller;

import com.esi.evaluation.model.Critere;
import com.esi.evaluation.service.CritereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/criteres")
public class CritereController {

    @Autowired
    private CritereService critereService;

    // Obtenir tous les critères
    @GetMapping
    public List<Critere> getAllCriteres() {
        return critereService.getAllCriteres();
    }

    // Obtenir un critère par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Critere> getCritereById(@PathVariable Long id) {
        Optional<Critere> critere = critereService.getCritereById(id);
        return critere.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Créer un nouveau critère
    @PostMapping
    public Critere createCritere(@RequestBody Critere critere) {
        return critereService.saveCritere(critere);
    }

    // Mettre à jour un critère
    @PutMapping("/{id}")
    public ResponseEntity<Critere> updateCritere(@PathVariable Long id, @RequestBody Critere critereDetails) {
        Optional<Critere> updatedCritere = critereService.updateCritere(id, critereDetails);
        return updatedCritere.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Supprimer un critère
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCritere(@PathVariable Long id) {
        boolean deleted = critereService.deleteCritere(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
