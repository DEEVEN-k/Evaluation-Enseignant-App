package com.esi.evaluation.repository;

import com.esi.evaluation.model.Critere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CritereRepository extends JpaRepository<Critere, Long> {

    // Récupérer tous les critères appartenant à un thème donné
    List<Critere> findByTheme(Critere.Theme theme);
}
