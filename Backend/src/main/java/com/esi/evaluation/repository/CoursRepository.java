package com.esi.evaluation.repository;

import com.esi.evaluation.model.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursRepository extends JpaRepository<Cours, Long> {

    // Rechercher les cours par id_enseignant
    List<Cours> findByEnseignantId(Long idEnseignant);

    // Rechercher un cours par son intitulé (optionnel selon besoin)
    List<Cours> findByIntituleContainingIgnoreCase(String intitule);
}
