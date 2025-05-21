package com.esi.evaluation.service;

import com.esi.evaluation.model.Etudiant;
import com.esi.evaluation.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    // Ajouter un étudiant
    public Etudiant ajouterEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    // Récupérer tous les étudiants
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    // Récupérer un étudiant par ID
    public Optional<Etudiant> getEtudiantById(Long id) {
        return etudiantRepository.findById(id);
    }

    // Supprimer un étudiant
    public void supprimerEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }

    // Mettre à jour un étudiant
    public Etudiant modifierEtudiant(Long id, Etudiant etudiantModifie) {
        return etudiantRepository.findById(id).map(etudiant -> {
            etudiant.setMatricule(etudiantModifie.getMatricule());
            etudiant.setClasse(etudiantModifie.getClasse());
            etudiant.setNiveau(etudiantModifie.getNiveau());
            return etudiantRepository.save(etudiant);
        }).orElseGet(() -> {
            etudiantModifie.setId(id);
            return etudiantRepository.save(etudiantModifie);
        });
    }

    // Rechercher un étudiant par matricule
    public Optional<Etudiant> getEtudiantByMatricule(String matricule) {
        return etudiantRepository.findByMatricule(matricule);
    }

    // Méthode équivalente à "updateEtudiant", version alternative de modification
    public Optional<Etudiant> updateEtudiant(Long id, Etudiant etudiantDetails) {
        return etudiantRepository.findById(id).map(existing -> {
            existing.setMatricule(etudiantDetails.getMatricule());
            existing.setClasse(etudiantDetails.getClasse());
            existing.setNiveau(etudiantDetails.getNiveau());
            return etudiantRepository.save(existing);
        });
    }

    // Méthode équivalente à ajouterEtudiant (alias)
    public Etudiant saveEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    // Supprimer et retourner un booléen selon succès
    public boolean deleteEtudiant(Long id) {
        if (etudiantRepository.existsById(id)) {
            etudiantRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
