package com.pidev.services;

import com.pidev.entities.Application;
import com.pidev.entities.ApplicationStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IApplicationService {
    void deleteApplication ( Long id ) throws Exception;

    Application updateStatus ( Long id, ApplicationStatus status ) throws Exception;

    Application submitApplication ( MultipartFile resume, MultipartFile coverLetter, Long jobOfferId, String userEmail ) throws IOException;

    public List<Application> getAllApplications();
    public List<Application> getApplicationsByUserEmail(String userEmail);
    public Map<String, Long> getGeneralStatistics();
    public Map<String, Long> getStatisticsByUserEmail(String userEmail);
    //public  void sendEmailForCandidate(String userEmail);
    public Map<String, Long> getStatistics(String userEmail);
}
