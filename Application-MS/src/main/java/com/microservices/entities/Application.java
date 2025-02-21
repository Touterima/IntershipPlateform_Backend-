package com.microservices.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Application {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Enumerated (EnumType.STRING)
    private ApplicationStatus status;

    private String resumeUrl;

    private String coverLetterUrl;

    private String applicationResponse;

    private String interviewResponse;

    private long jobOfferId;

    private String userEmail;

    // Constructor with required fields
    public Application(LocalDate date, ApplicationStatus status, String resumeUrl, String coverLetterUrl,
                       String applicationResponse, String interviewResponse, long jobOfferId, String userEmail) {
        this.date = date;
        this.status = status;
        this.resumeUrl = resumeUrl;
        this.coverLetterUrl = coverLetterUrl;
        this.applicationResponse = applicationResponse;
        this.interviewResponse = interviewResponse;
        this.jobOfferId = jobOfferId;
        this.userEmail = userEmail;
    }
    public String getResumeUrl () {
        return resumeUrl;
    }

    public void setResumeUrl ( String resumeUrl ) {
        this.resumeUrl = resumeUrl;
    }

    public void setDate ( LocalDate applicationDate ) {
        this.date = applicationDate;
    }

    public void setStatus ( ApplicationStatus status ) {
        this.status = status;
    }

    public void setInterviewResponse ( String interviewResponse ) {
        this.interviewResponse = interviewResponse;
    }

    public void setJobOfferId ( Long jobOfferId ) {
        this.jobOfferId = jobOfferId;
    }

    public void setUserEmail ( String userEmail ) {
        this.userEmail = userEmail;
    }

    public void setApplicationResponse ( String applicationResponse ) {
        this.applicationResponse = applicationResponse;
    }

    public void setCoverLetterUrl ( String coverLetterUrl ) {
        this.coverLetterUrl = coverLetterUrl;
    }

    public String getCoverLetterUrl () {
        return coverLetterUrl;
    }

    public LocalDate getDate () {
        return date;
    }

    public ApplicationStatus getStatus () {
        return status;
    }

    public String getApplicationResponse () {
        return applicationResponse;
    }

    public String getInterviewResponse () {
        return interviewResponse;
    }

    public String getUserEmail () {
        return userEmail;
    }

    public Long getJobOfferId () {
        return jobOfferId;
    }
}
