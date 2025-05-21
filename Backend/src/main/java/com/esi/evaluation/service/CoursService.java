package com.esi.evaluation.service;

import com.esi.evaluation.model.Cours;
import com.esi.evaluation.repository.CoursRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursService {

    private final CoursRepository coursRepository;

    // Injection de dépendances via constructeur (recommandée)
    public CoursService(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    /**
     * Ajouter un cours
     */
    public Cours ajouterCours(Cours cours) {
        return coursRepository.save(cours);
    }

    /**
     * Récupérer tous les cours
     */
    public List<Cours> getAllCours() {
        return coursRepository.findAll();
    }

    /**
     * Récupérer un cours par son ID
     */
    public Optional<Cours> getCoursById(Long id) {
        return coursRepository.findById(id);
    }

    /**
     * Supprimer un cours par son ID
     */
    public boolean supprimerCours(Long id) {
        if (coursRepository.existsById(id)) {
            coursRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Mettre à jour un cours existant
     */
    public Cours modifierCours(Long id, Cours coursModifie) {
        return coursRepository.findById(id).map(cours -> {
            cours.setIntitule(coursModifie.getIntitule());
            cours.setVolumeHoraire(coursModifie.getVolumeHoraire());
            cours.setEnseignant(coursModifie.getEnseignant());
            return coursRepository.save(cours);
        }).orElseGet(() -> {
            coursModifie.setId(id);
            return coursRepository.save(coursModifie);
        });
    }

    /**
     * Récupérer les cours d’un enseignant donné
     */
    public List<Cours> getCoursByEnseignantId(Long enseignantId) {
        return coursRepository.findByEnseignantId(enseignantId);
    }

    /**
     * Alternative à modifierCours : mise à jour simple avec exception si non trouvé
     */
    public Optional<Cours> updateCours(Long id, Cours coursDetails) {
        return coursRepository.findById(id).map(cours -> {
            cours.setIntitule(coursDetails.getIntitule());
            cours.setVolumeHoraire(coursDetails.getVolumeHoraire());
            cours.setEnseignant(coursDetails.getEnseignant());
            return coursRepository.save(cours);
        });
    }

    /**
     * Supprimer un cours (version avec retour booléen)
     */
    public boolean deleteCours(Long id) {
        return supprimerCours(id);
    }

    /**
     * Enregistrer un cours (générique)
     */
    public Cours saveCours(Cours cours) {
        return coursRepository.save(cours);
    }
}
