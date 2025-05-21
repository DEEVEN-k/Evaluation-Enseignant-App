package com.esi.evaluation.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_note")
    private Long id;

    @Column(name = "valeur_note", nullable = false)
    private int valeurNote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evaluation", nullable = false)
    private Evaluation evaluation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_critere", nullable = false)
    private Critere critere;

    // Constructeurs
    public Note() {}

    public Note(int valeurNote, Evaluation evaluation, Critere critere) {
        this.valeurNote = valeurNote;
        this.evaluation = evaluation;
        this.critere = critere;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Setter alternatif (optionnel mais demandé)
    public void setIdNote(Long id) {
        this.id = id;
    }

    public int getValeurNote() {
        return valeurNote;
    }

    public void setValeurNote(int valeurNote) {
        this.valeurNote = valeurNote;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public Critere getCritere() {
        return critere;
    }

    public void setCritere(Critere critere) {
        this.critere = critere;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", valeurNote=" + valeurNote +
                ", evaluationId=" + (evaluation != null ? evaluation.getId() : null) +
                ", critereId=" + (critere != null ? critere.getId() : null) +
                '}';
    }
}
