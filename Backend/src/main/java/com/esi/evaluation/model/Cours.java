package com.esi.evaluation.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cours")
public class Cours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cours")
    private Long id;

    @Column(name = "intitule", nullable = false)
    private String intitule;

    @Column(name = "volume_horaire")
    private int volumeHoraire;

    // Relation avec Enseignant
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_enseignant", nullable = false)
    private Enseignant enseignant;

    // Liste des évaluations liées à ce cours (mappedBy pour éviter la table intermédiaire)
    @OneToMany(mappedBy = "cours", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evaluation> evaluations;

    // Constructeurs
    public Cours() {}

    public Cours(String intitule, int volumeHoraire, Enseignant enseignant) {
        this.intitule = intitule;
        this.volumeHoraire = volumeHoraire;
        this.enseignant = enseignant;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public int getVolumeHoraire() {
        return volumeHoraire;
    }

    public void setVolumeHoraire(int volumeHoraire) {
        this.volumeHoraire = volumeHoraire;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "id=" + id +
                ", intitule='" + intitule + '\'' +
                ", volumeHoraire=" + volumeHoraire +
                ", enseignantId=" + (enseignant != null ? enseignant.getId() : null) +
                '}';
    }
}
