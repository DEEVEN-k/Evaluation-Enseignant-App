package com.esi.evaluation.service;

import com.esi.evaluation.model.Utilisateur;
import com.esi.evaluation.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    // === Ajouter un nouvel utilisateur ===
    public Utilisateur ajouterUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    // === Récupérer tous les utilisateurs ===
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    // === Récupérer un utilisateur par ID ===
    public Optional<Utilisateur> getUtilisateurById(Integer id) {
        return utilisateurRepository.findById(id);
    }

    // === Supprimer un utilisateur par ID ===
    public boolean supprimerUtilisateur(Integer id) {
        if (utilisateurRepository.existsById(id)) {
            utilisateurRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // === Modifier un utilisateur existant ===
    public Optional<Utilisateur> modifierUtilisateur(Integer id, Utilisateur utilisateurModifie) {
        return utilisateurRepository.findById(id).map(utilisateur -> {
            utilisateur.setNom(utilisateurModifie.getNom());
            utilisateur.setPrenom(utilisateurModifie.getPrenom());
            utilisateur.setEmail(utilisateurModifie.getEmail());
            utilisateur.setMotDePasse(utilisateurModifie.getMotDePasse());
            utilisateur.setRole(utilisateurModifie.getRole());
            utilisateur.setTelephone(utilisateurModifie.getTelephone());
            return utilisateurRepository.save(utilisateur);
        });
    }

    // === Rechercher un utilisateur par email ===
    public Optional<Utilisateur> getUtilisateurByEmail(String email) {
        return utilisateurRepository.findByEmail(email);
    }

    // === Récupérer les utilisateurs par rôle ===
    public List<Utilisateur> getUtilisateursByRole(Utilisateur.Role role) {
        return utilisateurRepository.findByRole(role);
    }

    // === Méthode générique pour ajouter ou mettre à jour ===
    public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    // === Mise à jour alternative ===
    public Utilisateur updateUtilisateur(Integer id, Utilisateur utilisateurModifie) {
        return modifierUtilisateur(id, utilisateurModifie)
                .orElseGet(() -> {
                    utilisateurModifie.setId(id);
                    return utilisateurRepository.save(utilisateurModifie);
                });
    }
}
