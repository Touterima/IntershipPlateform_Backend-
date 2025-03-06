package com.pidev.restController;

import com.pidev.entities.Application;
import com.pidev.entities.ApplicationStatus;
import com.pidev.services.IApplicationService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ApplicationRestController {
    @Autowired
    IApplicationService applicationService;


    @PostMapping ("/postuler")
    public ResponseEntity<Application> submitApplication ( @RequestParam ("resume") MultipartFile resume,
                                                           @RequestParam ("coverLetter") MultipartFile coverLetter,
                                                           @RequestParam ("jobOfferId") Long jobOfferId,
                                                           @RequestParam ("userEmail") String userEmail ) {
        try {

            Application application = applicationService.submitApplication(resume, coverLetter, jobOfferId, userEmail);
            System.out.println(resume);
            System.out.println(coverLetter);
            System.out.println(jobOfferId);
            System.out.println(userEmail);

            return new ResponseEntity<>(application, HttpStatus.CREATED);
        } catch ( IOException e ) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/all")
    public List<Application> getAllApplications () {
        return applicationService.getAllApplications();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable Long id) {
        try {
            applicationService.deleteApplication(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // Log the exception
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/update/{id}/{status}")
    public ResponseEntity<Application> updateStatus(@PathVariable Long id, @PathVariable ApplicationStatus status) {
        System.out.println("Received request to update application with ID: " + id + " to status: " + status);
        try {
            Application application = applicationService.updateStatus(id, status);
            return new ResponseEntity<>(application, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("Error updating application: " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }




    @GetMapping("/getByEmail")
    public List<Application> getApplicationsByUserEmail(@RequestParam String userEmail) {
        return applicationService.getApplicationsByUserEmail("imen.rachdi@esprit.tn");
    }

    @GetMapping("/statistics/user")
    public ResponseEntity<Map<String, Long>> getUserStatistics(@RequestParam String userEmail) {
        Map<String, Long> stats = applicationService.getStatisticsByUserEmail("imen.rachdi@esprit.tn");
        return ResponseEntity.ok(stats);
    }

    @GetMapping("/statistics/admin")
    public ResponseEntity<Map<String, Long>> getAdminStatistics() {
        Map<String, Long> stats = applicationService.getGeneralStatistics();
        return ResponseEntity.ok(stats);
    }


    @PostMapping ("/send")
    public ResponseEntity<Application> sendEmail(@RequestParam String email) {
           //applicationService.sendEmailForCandidate(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Long>> getStatistics(@RequestParam(required = false) String userEmail) {
        Map<String, Long> stats = applicationService.getStatistics("imen.rachdi@esprit.tn");
        return ResponseEntity.ok(stats);
    }
}