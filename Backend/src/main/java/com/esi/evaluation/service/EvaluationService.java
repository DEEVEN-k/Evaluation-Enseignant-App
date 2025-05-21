package com.esi.evaluation.service;

import com.esi.evaluation.model.Evaluation;
import com.esi.evaluation.model.Note;
import com.esi.evaluation.repository.EvaluationRepository;
import com.esi.evaluation.repository.NoteRepository;
import com.esi.evaluation.repository.CritereRepository;
import com.esi.evaluation.repository.EtudiantRepository;
import com.esi.evaluation.repository.CoursRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private CritereRepository critereRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private CoursRepository coursRepository;

    // Créer une évaluation avec ou sans notes
    public Evaluation createEvaluation(Evaluation evaluation) {
        // Vérifier que l'étudiant et le cours existent
        if (evaluation.getEtudiant() == null || evaluation.getCours() == null) {
            throw new IllegalArgumentException("L'étudiant ou le cours est manquant");
        }

        if (!etudiantRepository.existsById(evaluation.getEtudiant().getId()) ||
                !coursRepository.existsById(evaluation.getCours().getId())) {
            throw new IllegalArgumentException("Étudiant ou cours non trouvé");
        }

        Evaluation savedEvaluation = evaluationRepository.save(evaluation);

        // Sauvegarder les notes si présentes
        if (evaluation.getNotes() != null && !evaluation.getNotes().isEmpty()) {
            for (Note note : evaluation.getNotes()) {
                note.setEvaluation(savedEvaluation);
                noteRepository.save(note);
            }
        }

        return savedEvaluation;
    }

    // Récupérer toutes les évaluations
    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    // Récupérer une évaluation par ID
    public Evaluation getEvaluationById(Long id) {
        return evaluationRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new IllegalArgumentException("Évaluation non trouvée avec ID : " + id));
    }

    // Supprimer une évaluation
    public void deleteEvaluation(Long id) {
        if (!evaluationRepository.existsById(Math.toIntExact(id))) {
            throw new IllegalArgumentException("Évaluation non trouvée");
        }
        evaluationRepository.deleteById(Math.toIntExact(id));
    }

    // Mettre à jour une évaluation (par exemple : appreciation, date, année académique)
    public Evaluation updateEvaluation(Long id, Evaluation updatedEvaluation) {
        Evaluation existingEvaluation = evaluationRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new IllegalArgumentException("Évaluation non trouvée"));

        existingEvaluation.setAppreciation(updatedEvaluation.getAppreciation());
        existingEvaluation.setDateEvaluation(updatedEvaluation.getDateEvaluation());
        existingEvaluation.setAnneeAcademique(updatedEvaluation.getAnneeAcademique());

        return evaluationRepository.save(existingEvaluation);
    }

    // Ajouter des notes à une évaluation existante
    public Evaluation addNotesToEvaluation(Long evaluationId, List<Note> notes) {
        Evaluation evaluation = evaluationRepository.findById(Math.toIntExact(evaluationId))
                .orElseThrow(() -> new IllegalArgumentException("Évaluation non trouvée"));

        for (Note note : notes) {
            note.setEvaluation(evaluation);
            noteRepository.save(note);
        }

        return evaluation;
    }
}
