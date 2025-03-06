package com.pidev.dto;
import com.pidev.entities.ApplicationStatus;

public class ApplicationStatusUpdateDTO {
    private Long id;               // L'ID de l'application
    private ApplicationStatus status; // Le statut de l'application

    // Constructeurs
    public ApplicationStatusUpdateDTO() {}

    public ApplicationStatusUpdateDTO(Long id, ApplicationStatus status) {
        this.id = id;
        this.status = status;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}
