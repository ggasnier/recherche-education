package com.guillaumegasnier.education.recherche;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RechercheApplication {

    public static void main(String[] args) {
        SpringApplication.run(RechercheApplication.class, args);
    }

}
