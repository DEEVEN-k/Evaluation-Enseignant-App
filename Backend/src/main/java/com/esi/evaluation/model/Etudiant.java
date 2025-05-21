package com.esi.evaluation.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "etudiant")
public class Etudiant implements Serializable {

    @Id
    @Column(name = "id")
    private Long id; // Fait référence à Utilisateur.id

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Utilisateur utilisateur;

    @Column(name = "matricule", unique = true, nullable = false, length = 20)
    private String matricule;

    @Column(name = "classe", length = 50)
    private String classe;

    @Column(name = "niveau", length = 20)
    private String niveau;

    // ---------- Constructeurs ----------
    public Etudiant() {}

    public Etudiant(Utilisateur utilisateur, String matricule, String classe, String niveau) {
        this.utilisateur = utilisateur;
        this.matricule = matricule;
        this.classe = classe;
        this.niveau = niveau;
    }

    // ---------- Getters & Setters ----------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    // ---------- toString ----------
    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", matricule='" + matricule + '\'' +
                ", classe='" + classe + '\'' +
                ", niveau='" + niveau + '\'' +
                ", utilisateur=" + (utilisateur != null ? utilisateur.getNom() + " " + utilisateur.getPrenom() : "null") +
                '}';
    }
}
