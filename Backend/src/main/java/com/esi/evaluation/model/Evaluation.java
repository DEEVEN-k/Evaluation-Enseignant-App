package com.esi.evaluation.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Evaluation")
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evaluation")
    private Long id;

    @Column(name = "date_evaluation", nullable = false)
    private LocalDate dateEvaluation;

    @Column(name = "annee_academique", nullable = false, length = 9) // ex: "2024-2025"
    private String anneeAcademique;

    @Column(columnDefinition = "TEXT")
    private String appreciation;

    // Relation vers Etudiant
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etudiant", nullable = false)
    private Etudiant etudiant;

    // Relation vers Cours
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cours", nullable = false)
    private Cours cours;

    // Si une évaluation a des notes détaillées
    @OneToMany(mappedBy = "evaluation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Note> notes;

    // Constructeurs
    public Evaluation() {}

    public Evaluation(LocalDate dateEvaluation, String anneeAcademique, String appreciation, Etudiant etudiant, Cours cours) {
        this.dateEvaluation = dateEvaluation;
        this.anneeAcademique = anneeAcademique;
        this.appreciation = appreciation;
        this.etudiant = etudiant;
        this.cours = cours;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateEvaluation() {
        return dateEvaluation;
    }

    public void setDateEvaluation(LocalDate dateEvaluation) {
        this.dateEvaluation = dateEvaluation;
    }

    public String getAnneeAcademique() {
        return anneeAcademique;
    }

    public void setAnneeAcademique(String anneeAcademique) {
        this.anneeAcademique = anneeAcademique;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "id=" + id +
                ", dateEvaluation=" + dateEvaluation +
                ", anneeAcademique='" + anneeAcademique + '\'' +
                ", appreciation='" + appreciation + '\'' +
                ", etudiant=" + (etudiant != null ? etudiant.getId() : null) +
                ", cours=" + (cours != null ? cours.getId() : null) +
                ", notes=" + (notes != null ? notes.size() + " note(s)" : "aucune") +
                '}';
    }
}
