package com.esi.evaluation.service;

import com.esi.evaluation.model.Note;
import com.esi.evaluation.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    // === Récupérer toutes les notes ===
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // === Récupérer une note par son ID ===
    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    // === Ajouter une nouvelle note ===
    public Note ajouterNote(Note note) {
        return noteRepository.save(note);
    }

    // === Sauvegarder une note (ajout ou mise à jour) ===
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    // === Supprimer une note par ID ===
    public boolean deleteNote(Long id) {
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // === Modifier une note (avec création si absente) ===
    public Note modifierNote(Long id, Note noteModifiee) {
        return noteRepository.findById(id).map(note -> {
            note.setValeurNote(noteModifiee.getValeurNote());
            note.setEvaluation(noteModifiee.getEvaluation());
            note.setCritere(noteModifiee.getCritere());
            return noteRepository.save(note);
        }).orElseGet(() -> {
            noteModifiee.setIdNote(id);
            return noteRepository.save(noteModifiee);
        });
    }

    // === Mise à jour alternative ===
    public Optional<Note> updateNote(Long id, Note noteDetails) {
        return noteRepository.findById(id).map(note -> {
            note.setValeurNote(noteDetails.getValeurNote());
            note.setEvaluation(noteDetails.getEvaluation());
            note.setCritere(noteDetails.getCritere());
            return noteRepository.save(note);
        });
    }

    // === Récupérer les notes liées à une évaluation ===
    public List<Note> getNotesByEvaluationId(Long idEvaluation) {
        return noteRepository.findByEvaluationIdEvaluation(idEvaluation);
    }
}
