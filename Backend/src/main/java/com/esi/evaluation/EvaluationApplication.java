package com.esi.evaluation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Point d'entrée principal de l'application Spring Boot.
 * Cette classe initialise le contexte Spring et démarre l'application.
 */
@SpringBootApplication(scanBasePackages = "com.esi.evaluation")
public class EvaluationApplication {

    public static void main(String[] args) {
        // Démarrage de l'application avec Spring Boot
        SpringApplication.run(EvaluationApplication.class, args);
    }
}
