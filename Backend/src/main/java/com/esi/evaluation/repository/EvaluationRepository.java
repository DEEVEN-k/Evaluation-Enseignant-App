package com.esi.evaluation.repository;

import com.esi.evaluation.model.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {

    // Toutes les évaluations d'un étudiant
    List<Evaluation> findByEtudiant_Id(Integer idEtudiant);

    // Toutes les évaluations d’un cours
    List<Evaluation> findByCours_IdCours(Integer idCours);

    // Toutes les évaluations d’une année académique
    List<Evaluation> findByAnneeAcademique(String anneeAcademique);

    // Évaluations par étudiant et année
    List<Evaluation> findByEtudiant_IdAndAnneeAcademique(Integer idEtudiant, String anneeAcademique);

    // Évaluations par cours et année
    List<Evaluation> findByCours_IdCoursAndAnneeAcademique(Integer idCours, String anneeAcademique);
}
