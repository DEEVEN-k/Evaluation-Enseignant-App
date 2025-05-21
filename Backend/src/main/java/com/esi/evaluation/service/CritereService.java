package com.esi.evaluation.service;

import com.esi.evaluation.model.Critere;
import com.esi.evaluation.repository.CritereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CritereService {

    private final CritereRepository critereRepository;

    @Autowired
    public CritereService(CritereRepository critereRepository) {
        this.critereRepository = critereRepository;
    }

    // === Récupérer tous les critères ===
    public List<Critere> getAllCriteres() {
        return critereRepository.findAll();
    }

    // === Récupérer un critère par ID ===
    public Optional<Critere> getCritereById(Long id) {
        return critereRepository.findById(id);
    }

    // === Ajouter un critère ===
    public Critere ajouterCritere(Critere critere) {
        return critereRepository.save(critere);
    }

    // === Sauvegarder un critère (ajout ou mise à jour) ===
    public Critere saveCritere(Critere critere) {
        return critereRepository.save(critere);
    }

    // === Supprimer un critère par ID (version booléenne) ===
    public boolean deleteCritere(Long id) {
        if (critereRepository.existsById(id)) {
            critereRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // === Supprimer un critère (ancienne méthode) ===
    public void supprimerCritere(Long id) {
        critereRepository.deleteById(id);
    }

    // === Mettre à jour un critère par ID (avec fallback si inexistant) ===
    public Critere modifierCritere(Long id, Critere critereModifie) {
        return critereRepository.findById(id).map(critere -> {
            critere.setTheme(critereModifie.getTheme());
            critere.setCritere(critereModifie.getCritere());
            return critereRepository.save(critere);
        }).orElseGet(() -> {
            critereModifie.setId(id);
            return critereRepository.save(critereModifie);
        });
    }

    // === Mettre à jour un critère sans fallback ===
    public Optional<Critere> updateCritere(Long id, Critere critereDetails) {
        return critereRepository.findById(id).map(critere -> {
            critere.setTheme(critereDetails.getTheme());
            critere.setCritere(critereDetails.getCritere());
            return critereRepository.save(critere);
        });
    }

    // === Récupérer les critères d'un thème spécifique ===
    public List<Critere> getCriteresByTheme(String theme) {
        try {
            Critere.Theme themeEnum = Critere.Theme.valueOf(theme.toUpperCase());
            return critereRepository.findByTheme(themeEnum);
        } catch (IllegalArgumentException e) {
            // Retourne une liste vide si le thème est invalide
            return List.of();
        }
    }
}
