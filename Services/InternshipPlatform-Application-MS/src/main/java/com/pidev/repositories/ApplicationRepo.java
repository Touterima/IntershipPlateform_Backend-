package com.pidev.repositories;

import com.pidev.entities.Application;
import com.pidev.entities.ApplicationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Long> {
    List<Application> findByJobOfferId(Long jobOfferId);
    List<Application> findByStatus( ApplicationStatus status);
    List<Application> findByUserEmail(String userEmail);
    String getEmailById(Long id);
    long countByUserEmail(String userEmail);
    long countByStatusAndUserEmail(ApplicationStatus status, String userEmail);
    long countByStatus(ApplicationStatus status);


}