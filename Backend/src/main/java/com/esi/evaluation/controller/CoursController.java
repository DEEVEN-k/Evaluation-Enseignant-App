package com.esi.evaluation.controller;

import com.esi.evaluation.model.Cours;
import com.esi.evaluation.service.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cours")
public class CoursController {

    @Autowired
    private CoursService coursService;

    // Obtenir tous les cours
    @GetMapping
    public List<Cours> getAllCours() {
        return coursService.getAllCours();
    }

    // Obtenir un cours par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Cours> getCoursById(@PathVariable Long id) {
        return coursService.getCoursById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Créer un nouveau cours
    @PostMapping
    public Cours createCours(@RequestBody Cours cours) {
        return coursService.saveCours(cours);
    }

    // Mettre à jour un cours
    @PutMapping("/{id}")
    public ResponseEntity<Cours> updateCours(@PathVariable Long id, @RequestBody Cours coursDetails) {
        return (ResponseEntity<Cours>) coursService.updateCours(id, coursDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Supprimer un cours
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCours(@PathVariable Long id) {
        boolean deleted = coursService.deleteCours(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
