package com.microservices;

import com.microservices.entities.Application;
import com.microservices.entities.ApplicationStatus;
import com.microservices.repositories.ApplicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@EnableDiscoveryClient
@SpringBootApplication
public class ApplicationMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMsApplication.class, args);
    }

    @Autowired
    private ApplicationRepo repository;

    @Bean
    ApplicationRunner init() {
        return args -> {
            // Check if the database is empty before adding sample data
            if (repository.count() == 0) {
                // Save example applications with some sample data
                saveTestApplications();
            } else {
                System.out.println("The data already exists!");
            }
        };
    }

    private void saveTestApplications() {
        Application app1 = new Application(LocalDate.now(), ApplicationStatus.SUBMITTED,
                "/uploads/1_cv.pdf", "/uploads/1_cv.pdf",
                "Pending response", "Pending response", 1L, "user1@example.com");

        Application app2 = new Application(LocalDate.now(), ApplicationStatus.ACCEPTED,
                "/uploads/1_cv.pdf", "/uploads/1_cv.pdf",
                "Pending response", "Pending response", 2L, "user2@example.com");

        Application app3 = new Application(LocalDate.now(), ApplicationStatus.ACCEPTED,
                "/uploads/2_cv.pdf", "/uploads/2_cv.pdf",
                "Pending response", "Pending response", 3L, "user3@example.com");
        // Save both applications
        repository.save(app1);
        repository.save(app2);
        repository.save(app3);

        // Print all applications to the console
        repository.findAll().forEach(System.out::println);
    }
}
