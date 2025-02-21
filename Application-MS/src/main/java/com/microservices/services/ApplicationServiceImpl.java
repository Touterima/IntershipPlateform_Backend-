package com.microservices.services;

import com.microservices.entities.Application;
import com.microservices.entities.ApplicationStatus;
import com.microservices.repositories.ApplicationRepo;
import com.microservices.restController.ApplicationRestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor // Automatically generates a constructor with required arguments

public class ApplicationServiceImpl implements IApplicationService {

    @Autowired
    ApplicationRepo applicationRepo;  // Constructor injection for ApplicationRepo
    private static final Logger log = LoggerFactory.getLogger(ApplicationServiceImpl.class);

    @Value("${upload.dir}")  // Configurable upload directory
    private String uploadDir;

    // Handle file upload
    public String uploadFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is required");
        }

        // Vérification du type de fichier
        String contentType = file.getContentType();
        if (contentType == null || (!contentType.equals("application/pdf") && !contentType.equals("application/msword"))) {
            throw new IllegalArgumentException("Only PDF and Word files are allowed");
        }

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath); // Ensure directory exists
        }

        // Générer un nom de fichier unique
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);

        log.info("Uploading file: {}", fileName);

        Files.write(filePath, file.getBytes());
        return "/uploads/" + fileName;
    }

    // Soumettre une candidature (anciennement 'postuler')
    @Override
    public Application submitApplication(MultipartFile resume, MultipartFile coverLetter, Long jobOfferId, String userEmail) throws IOException {
        log.info("Processing application for user: {} for job offer ID: {}", userEmail, jobOfferId);

        String resumeUrl = uploadFile(resume);
        String coverLetterUrl = uploadFile(coverLetter);

        Application application = new Application(
                LocalDate.now(),
                ApplicationStatus.SUBMITTED, // Statut "soumis" initial
                resumeUrl,
                coverLetterUrl,
                "Pending response",
                "Pending response",
                jobOfferId,
                userEmail
        );

        log.info("Saving application for user: {}", userEmail);
        return applicationRepo.save(application);
    }

    // Mettre à jour le statut de la candidature
    @Override
    public Application updateStatus(Long id, ApplicationStatus status) throws Exception {
        Application application = applicationRepo.findById(id)
                .orElseThrow(() -> new Exception("Candidature not found"));


        application.setStatus(status);
        return applicationRepo.save(application);
    }

    // Obtenir toutes les candidatures
    @Override
    public List<Application> getAllApplications() {
        return applicationRepo.findAll();
    }

    // Supprimer une candidature
    @Override
    public void deleteApplication(Long id) {
        log.info("Deleting application with ID: {}", id);
        applicationRepo.deleteById(id);
    }
}
