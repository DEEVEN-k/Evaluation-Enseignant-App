package com.esi.evaluation.service;

import com.esi.evaluation.model.Enseignant;
import com.esi.evaluation.repository.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnseignantService {

    @Autowired
    private EnseignantRepository enseignantRepository;

    // Ajouter un enseignant
    public Enseignant ajouterEnseignant(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    // Récupérer la liste de tous les enseignants
    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }

    // Récupérer un enseignant par son ID
    public Optional<Enseignant> getEnseignantById(Long id) {
        return enseignantRepository.findById(id);
    }

    // Supprimer un enseignant par ID
    public boolean supprimerEnseignant(Long id) {
        if (enseignantRepository.existsById(id)) {
            enseignantRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Modifier les informations d’un enseignant
    public Enseignant modifierEnseignant(Long id, Enseignant enseignantModifie) {
        return enseignantRepository.findById(id).map(enseignant -> {
            enseignant.setNom(enseignantModifie.getNom());
            enseignant.setPrenom(enseignantModifie.getPrenom());
            enseignant.setEmail(enseignantModifie.getEmail());
            enseignant.setTelephone(enseignantModifie.getTelephone());
            return enseignantRepository.save(enseignant);
        }).orElseGet(() -> {
            enseignantModifie.setId(Math.toIntExact(id));
            return enseignantRepository.save(enseignantModifie);
        });
    }

    // Vérifier s'il existe un enseignant avec un email donné
    public boolean existeParEmail(String email) {
        return enseignantRepository.existsByEmail(email);
    }

    // Rechercher les enseignants par nom ou prénom
    public List<Enseignant> rechercherParNomOuPrenom(String motCle) {
        return enseignantRepository.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(motCle, motCle);
    }
}
