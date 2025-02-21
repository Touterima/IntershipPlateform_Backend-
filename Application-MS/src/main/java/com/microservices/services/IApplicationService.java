package com.microservices.services;

import com.microservices.entities.Application;
import com.microservices.entities.ApplicationStatus;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IApplicationService {
    public List<Application> getAllApplications() ;
    public void deleteApplication(Long id);
    public Application updateStatus(Long id, ApplicationStatus status) throws Exception ;
    public Application submitApplication(MultipartFile resume, MultipartFile coverLetter, Long jobOfferId, String userEmail) throws IOException;


    }
