package com.esi.evaluation.repository;

import com.esi.evaluation.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    // Recherche d'un utilisateur par email
    Optional<Utilisateur> findByEmail(String email);

    // Recherche d'un utilisateur par nom (optionnel)
    Optional<Utilisateur> findByNom(String nom);

    // Recherche combinée (optionnel)
    Optional<Utilisateur> findByNomAndPrenom(String nom, String prenom);

    List<Utilisateur> findByRole(Utilisateur.Role role);
}
