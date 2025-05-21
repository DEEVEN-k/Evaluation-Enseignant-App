package com.esi.evaluation.controller;

import com.esi.evaluation.model.Evaluation;
import com.esi.evaluation.model.Note;
import com.esi.evaluation.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/evaluations")
@CrossOrigin(origins = "*")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    // Ajouter une nouvelle évaluation avec ses notes
    @PostMapping
    public Evaluation createEvaluation(@RequestBody Evaluation evaluation) {
        return evaluationService.createEvaluation(evaluation);
    }

    // Récupérer toutes les évaluations
    @GetMapping
    public List<Evaluation> getAllEvaluations() {
        return evaluationService.getAllEvaluations();
    }

    // Récupérer une évaluation par son ID
    @GetMapping("/{id}")
    public Evaluation getEvaluationById(@PathVariable Long id) {
        return evaluationService.getEvaluationById(id);
    }

    // Supprimer une évaluation
    @DeleteMapping("/{id}")
    public void deleteEvaluation(@PathVariable Long id) {
        evaluationService.deleteEvaluation(id);
    }

    // Mettre à jour une évaluation (ex : appreciation ou date)
    @PutMapping("/{id}")
    public Evaluation updateEvaluation(@PathVariable Long id, @RequestBody Evaluation evaluation) {
        return evaluationService.updateEvaluation(id, evaluation);
    }

    // Ajouter une note à une évaluation existante
    @PostMapping("/{evaluationId}/notes")
    public Evaluation addNotesToEvaluation(@PathVariable Long evaluationId, @RequestBody List<Note> notes) {
        return evaluationService.addNotesToEvaluation(evaluationId, notes);
    }
}
