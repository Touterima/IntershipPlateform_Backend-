package tn.pidev.intershipplateform_backend.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import tn.pidev.intershipplateform_backend.entities.InternshipType;
import tn.pidev.intershipplateform_backend.entities.InternshipValidationStatus;
import tn.pidev.intershipplateform_backend.entities.Mention;

import java.util.Date;

public class InternShipCreateDto {


    @Enumerated(EnumType.STRING)
    @NotNull(message = "Le type de stage ne doit pas être nul")
    private InternshipType type;
    @Email(message = "L'email du superviseur doit être valide")
    @NotBlank(message = "L'email du superviseur ne doit pas être vide")
    private String supervisorEmail;
    @NotBlank(message = "L'adresse de la societe ne doit pas être vide")
    private String companyAddress;
    @NotBlank(message = "Nom de la societe ne doit pas être vide")
    private String companyName;

    public @NotNull(message = "Le type de stage ne doit pas être nul") InternshipType getType() {
        return type;
    }

    public void setType(@NotNull(message = "Le type de stage ne doit pas être nul") InternshipType type) {
        this.type = type;
    }



    public InternShipCreateDto() {
    }

    public InternShipCreateDto(InternshipType type, String supervisorEmail) {
        this.type = type;
        this.supervisorEmail = supervisorEmail;
    }

    public @Email(message = "L'email du superviseur doit être valide") @NotBlank(message = "L'email du superviseur ne doit pas être vide") String getSupervisorEmail() {
        return supervisorEmail;
    }

    public void setSupervisorEmail(@Email(message = "L'email du superviseur doit être valide") @NotBlank(message = "L'email du superviseur ne doit pas être vide") String supervisorEmail) {
        this.supervisorEmail = supervisorEmail;
    }

    public @NotBlank(message = "L'adresse de la societe ne doit pas être vide") String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(@NotBlank(message = "L'adresse de la societe ne doit pas être vide") String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public @NotBlank(message = "Nom de la societe ne doit pas être vide") String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(@NotBlank(message = "Nom de la societe ne doit pas être vide") String companyName) {
        this.companyName = companyName;
    }
}
