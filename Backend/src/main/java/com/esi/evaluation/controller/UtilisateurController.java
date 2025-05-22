package com.esi.evaluation.controller;

import com.esi.evaluation.model.Utilisateur;
import com.esi.evaluation.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "*") // à restreindre en production
public class UtilisateurController {

    @Autowired
    private UtilisateurService utilisateurService;

    // === Créer un utilisateur ===
    @PostMapping
    public ResponseEntity<Utilisateur> ajouterUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur nouveau = utilisateurService.ajouterUtilisateur(utilisateur);
        return ResponseEntity.ok(nouveau);
    }

    // === Lister tous les utilisateurs ===
    @GetMapping
    public ResponseEntity<List<Utilisateur>> getAllUtilisateurs() {
        return ResponseEntity.ok(utilisateurService.getAllUtilisateurs());
    }

    // === Récupérer un utilisateur par ID ===
    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable Integer id) {
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurById(id);
        return utilisateur.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // === Supprimer un utilisateur par ID ===
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerUtilisateur(@PathVariable Integer id) {
        if (utilisateurService.supprimerUtilisateur(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // === Mettre à jour un utilisateur ===
    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> modifierUtilisateur(@PathVariable Integer id, @RequestBody Utilisateur utilisateur) {
        Optional<Utilisateur> modifie = utilisateurService.modifierUtilisateur(id, utilisateur);
        return modifie.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // === Récupérer un utilisateur par email ===
    @GetMapping("/email/{email}")
    public ResponseEntity<Utilisateur> getUtilisateurByEmail(@PathVariable String email) {
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateurByEmail(email);
        return utilisateur.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // === Récupérer tous les utilisateurs d’un rôle ===
    @GetMapping("/role/{role}")
    public ResponseEntity<List<Utilisateur>> getUtilisateursByRole(@PathVariable Utilisateur.Role role) {
        return ResponseEntity.ok(utilisateurService.getUtilisateursByRole(role));
    }

    // === Authentification (login) ===
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Utilisateur loginRequest) {
        Optional<Utilisateur> utilisateurOpt = utilisateurService.getUtilisateurByEmail(loginRequest.getEmail());

        if (utilisateurOpt.isPresent()) {
            Utilisateur utilisateur = utilisateurOpt.get();
            if (utilisateur.getMotDePasse().equals(loginRequest.getMotDePasse())) {
                return ResponseEntity.ok(utilisateur); // OK: credentials valid
            } else {
                return ResponseEntity.status(401).body("Mot de passe incorrect");
            }
        } else {
            return ResponseEntity.status(404).body("Utilisateur non trouvé");
        }
    }
}
