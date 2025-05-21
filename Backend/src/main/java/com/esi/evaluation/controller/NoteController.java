package com.esi.evaluation.controller;

import com.esi.evaluation.model.Note;
import com.esi.evaluation.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    // Obtenir toutes les notes
    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    // Obtenir une note par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Créer une nouvelle note
    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return noteService.saveNote(note);
    }

    // Mettre à jour une note existante
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note noteDetails) {
        return (ResponseEntity<Note>) noteService.updateNote(id, noteDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Supprimer une note
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        boolean deleted = noteService.deleteNote(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
