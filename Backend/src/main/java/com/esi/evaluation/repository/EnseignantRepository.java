package com.esi.evaluation.repository;

import com.esi.evaluation.model.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {

    // Vérifie si un enseignant existe par son adresse email
    boolean existsByEmail(String email);

    // Rechercher par nom ou prénom (recherche partielle et insensible à la casse)
    List<Enseignant> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom);
}
