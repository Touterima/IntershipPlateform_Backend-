package tn.pidev.intershipplateform_backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.Date;

@Entity
public class Internship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInternship;

    @Enumerated(EnumType.STRING)
     private InternshipType type;

      private String certificateFile;
    private String internshipReport;

     private InternshipValidationStatus status;


      private Date presentationDate;

     private String grade;

    @Enumerated(EnumType.STRING)
      private Mention mention;

    @Email(message = "L'email du superviseur doit être valide")
    @NotBlank(message = "L'email du superviseur ne doit pas être vide")
    private String supervisorEmail;


    private String companyName;
    private String companyAddress;

    // Constructeurs
    public Internship() {}

    public Internship(Long idInternship, InternshipType type, String certificateFile, String internshipReport,
                      InternshipValidationStatus status, Boolean supervisorSigned, Date presentationDate,
                      String grade, Mention mention, String supervisorEmail) {
        this.idInternship = idInternship;
        this.type = type;
        this.certificateFile = certificateFile;
        this.internshipReport = internshipReport;
        this.status = status;
         this.presentationDate = presentationDate;
        this.grade = grade;
        this.mention = mention;
        this.supervisorEmail = supervisorEmail;
    }
    public Internship(InternshipType type,String supervisorEmail,String CompanyName,String companyAddress) {
        this.type = type;
        this.supervisorEmail=supervisorEmail;
        this.companyAddress=companyAddress;
        this.companyName=CompanyName;
    }

    // Getters et Setters

    public Long getIdInternship() {
        return idInternship;
    }

    public void setIdInternship(Long idInternship) {
        this.idInternship = idInternship;
    }

    public InternshipType getType() {
        return type;
    }

    public void setType(InternshipType type) {
        this.type = type;
    }

    public String getCertificateFile() {
        return certificateFile;
    }

    public void setCertificateFile(String certificateFile) {
        this.certificateFile = certificateFile;
    }

    public String getInternshipReport() {
        return internshipReport;
    }

    public void setInternshipReport(String internshipReport) {
        this.internshipReport = internshipReport;
    }

    public InternshipValidationStatus getStatus() {
        return status;
    }

    public void setStatus(InternshipValidationStatus status) {
        this.status = status;
    }


    public Date getPresentationDate() {
        return presentationDate;
    }

    public void setPresentationDate(Date presentationDate) {
        this.presentationDate = presentationDate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Mention getMention() {
        return mention;
    }

    public void setMention(Mention mention) {
        this.mention = mention;
    }

    public String getSupervisorEmail() {
        return supervisorEmail;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public void setSupervisorEmail(String supervisorEmail) {
        this.supervisorEmail = supervisorEmail;
    }
}