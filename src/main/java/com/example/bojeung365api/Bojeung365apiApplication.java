package com.example.bojeung365api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Bojeung365apiApplication {

    public static void main(String[] args) {
        SpringApplication.run(Bojeung365apiApplication.class, args);
    }

}
