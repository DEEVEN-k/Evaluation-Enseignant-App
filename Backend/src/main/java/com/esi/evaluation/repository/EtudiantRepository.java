package com.esi.evaluation.repository;

import com.esi.evaluation.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    // Rechercher un étudiant par son matricule
    Optional<Etudiant> findByMatricule(String matricule);

    // Rechercher tous les étudiants d'une classe donnée
    Iterable<Etudiant> findByClasse(String classe);

    // Rechercher tous les étudiants d’un niveau
    Iterable<Etudiant> findByNiveau(String niveau);
}
