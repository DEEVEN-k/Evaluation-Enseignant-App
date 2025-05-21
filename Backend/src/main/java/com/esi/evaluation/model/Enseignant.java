package com.esi.evaluation.model;

import jakarta.persistence.*;

@Entity
@Table(name = "enseignants")
public class Enseignant {

    @Id
    private Integer id; // correspond à Utilisateur.id

    @Column(name = "matricule_enseignant", unique = true, nullable = false)
    private String matriculeEnseignant;

    @Column(name = "specialite")
    private String specialite;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @MapsId
    private Utilisateur utilisateur;

    // === Constructeurs ===
    public Enseignant() {}

    public Enseignant(String matriculeEnseignant, String specialite, Utilisateur utilisateur) {
        this.matriculeEnseignant = matriculeEnseignant;
        this.specialite = specialite;
        this.utilisateur = utilisateur;
    }

    // === Getters et Setters ===
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatriculeEnseignant() {
        return matriculeEnseignant;
    }

    public void setMatriculeEnseignant(String matriculeEnseignant) {
        this.matriculeEnseignant = matriculeEnseignant;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    // === Accès délégué aux infos utilisateur ===
    public String getNom() {
        return utilisateur != null ? utilisateur.getNom() : null;
    }

    public String getPrenom() {
        return utilisateur != null ? utilisateur.getPrenom() : null;
    }

    public String getEmail() {
        return utilisateur != null ? utilisateur.getEmail() : null;
    }

    public String getTelephone() {
        return utilisateur != null ? utilisateur.getTelephone() : null;
    }

    @Override
    public String toString() {
        return "Enseignant{" +
                "id=" + id +
                ", matriculeEnseignant='" + matriculeEnseignant + '\'' +
                ", specialite='" + specialite + '\'' +
                ", utilisateur=" + (utilisateur != null ? utilisateur.getNom() + " " + utilisateur.getPrenom() : null) +
                '}';
    }

    public void setTelephone(String telephone) {
    }

    public void setEmail(String email) {
    }

    public void setPrenom(String prenom) {
    }

    public void setNom(String nom) {

    }
}
