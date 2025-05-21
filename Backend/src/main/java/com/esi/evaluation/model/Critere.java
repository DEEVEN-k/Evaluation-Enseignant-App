package com.esi.evaluation.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Critere")
public class Critere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_critere")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Theme theme;

    @Enumerated(EnumType.STRING)
    private CritereLabel critere;

    // Constructeurs
    public Critere() {
    }

    public Critere(Theme theme, CritereLabel critere) {
        this.theme = theme;
        this.critere = critere;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public CritereLabel getCritere() {
        return critere;
    }

    public void setCritere(CritereLabel critere) {
        this.critere = critere;
    }

    @Override
    public String toString() {
        return "Critere{" +
                "id=" + id +
                ", theme=" + theme +
                ", critere=" + critere +
                '}';
    }

    // Enums internes
    public enum Theme {
        INTERET_DE_L_ENSEIGNANT_POUR_SON_COURS,
        CLARTE_DU_COURS,
        RELATIONS_AVEC_LES_APPRENANTS,
        ORGANISATION_DU_COURS,
        INCITATION_A_LA_PARTICIPATION,
        EXPLICATIONS,
        ATTITUDE_DES_APPRENANTS_AUTO_PERCEPTION
    }

    public enum CritereLabel {
        // INTERET_DE_L_ENSEIGNANT_POUR_SON_COURS
        Cherche_a_donner_lenvie_dapprendre,
        Fait_preuve_dhumour,
        Utilise_differents_supports_pedagogiques,
        Lit_ses_notes_ou_un_document_ecrit,
        Suggere_des_implications_pratiques,
        Donne_son_point_de_vue_personnel,
        Fait_preuve_de_conviction_dans_son_cours,
        Montre_de_l_interet_et_de_l_enthousiasme,

        // CLARTE_DU_COURS
        Utilise_un_debit_de_parole_approprie,
        Parle_d_une_voix_non_monotone,
        S_exprime_clairement,
        Avance_dans_son_cours_a_une_vitesse_mesuree,

        // RELATIONS_AVEC_LES_APPRENANTS
        Montre_de_l_interet_pour_les_apprenants,
        Accepte_les_points_de_vue_divergents,
        Apporte_de_laide_en_cas_d_incomprehension,
        Montre_du_respect_envers_les_apprenants,
        Est_d_un_contact_facile,
        Maintien_de_la_discipline_dans_la_classe,
        Tolere_les_retards_aux_cours,
        Sanctionne_les_absences_non_justifiees_au_cours_precedent,

        // ORGANISATION_DU_COURS
        Presente_les_objectifs_du_cours_a_atteindre,
        Dispense_le_cours_de_maniere_organisee,
        Donne_des_documents_de_travail,
        Fait_des_transitions_logiques_entre_les_competences_a_acquerir,
        Fait_des_syntheses_utiles_lors_de_son_cours,
        Fait_preuve_de_rigueur_dans_ses_horaires_de_cours,

        // INCITATION_A_LA_PARTICIPATION
        Encourage_les_questions_et_les_commentaires,
        Interroge_individuellement_les_apprenants,
        Pose_des_questions_a_la_classe_entiere,
        Incite_les_apprenants_a_interagir,
        Pose_des_questions_complexes,
        Cree_une_ambiance_animee_et_ordonee,
        Maintien_en_eveil_de_l_attention_des_apprenants,

        // EXPLICATIONS
        Utilise_des_explications,
        Repete_ce_qui_est_difficile,
        Souligne_les_points_centraux,
        Donne_des_details,
        Identifie_les_points_cles,
        Donne_des_travaux_de_maison,
        Fait_des_corrections_ponctuelles_des_travaux,

        // ATTITUDE_DES_APPRENANTS
        Sont_attentifs_et_appliques,
        Participent_activement_aux_cours,
        Respectent_l_enseignant,
        Sont_motives_par_le_cours
    }
}
