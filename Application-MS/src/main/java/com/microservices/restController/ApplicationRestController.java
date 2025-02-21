package com.microservices.restController;
import com.microservices.entities.Application;
import com.microservices.services.IApplicationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/applications")
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRestController {

    @Autowired
    private IApplicationService applicationService;
    private static final Logger log = LoggerFactory.getLogger(ApplicationRestController.class);

    @PostMapping("/submit")
    public ResponseEntity<?> submitApplication(
            @RequestParam("resume") MultipartFile resume,
            @RequestParam("coverLetter") MultipartFile coverLetter,
            @RequestParam("jobOfferId") Long jobOfferId,
            @RequestParam("userEmail") String userEmail) {
        try {
            // Validate file inputs
            if (resume.isEmpty() || coverLetter.isEmpty()) {
                log.warn("Application submission failed: Missing resume or cover letter.");
                return ResponseEntity.badRequest().body("Both resume and cover letter must be provided.");
            }

            // Process the application
            Application application = applicationService.submitApplication(resume, coverLetter, jobOfferId, userEmail);
            log.info("Application successfully submitted for user: {}", userEmail);
            return ResponseEntity.ok(application);
        } catch (IllegalStateException e) {
            log.error("Conflict error: {}", e.getMessage());
            return ResponseEntity.status(409).body(e.getMessage()); // Conflict (if already applied)
        } catch (Exception e) {
            log.error("Error processing application: {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Error processing the application: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Application>> getAllApplications() {
        try {
            List<Application> applications = applicationService.getAllApplications();
            log.info("Retrieved {} applications.", applications.size());
            return ResponseEntity.ok(applications);
        } catch (Exception e) {
            log.error("Error retrieving applications: {}", e.getMessage());
            return ResponseEntity.internalServerError().body(null);
        }
    }
}

