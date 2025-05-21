package com.esi.evaluation.repository;

import com.esi.evaluation.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    // Récupérer toutes les notes associées à une évaluation spécifique
    List<Note> findByEvaluationId(Long evaluationId);

    // Récupérer toutes les notes associées à un critère spécifique
    List<Note> findByCritereId(Long critereId);

    List<Note> findByEvaluationIdEvaluation(Long idEvaluation);
}
