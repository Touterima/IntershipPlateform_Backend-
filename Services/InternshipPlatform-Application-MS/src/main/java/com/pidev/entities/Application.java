package com.pidev.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
public class Application {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId () {
        return id;
    }

    public void setId ( Long id ) {
        this.id = id;
    }

    public void setJobOfferId ( long jobOfferId ) {
        this.jobOfferId = jobOfferId;
    }

    public Application ( LocalDate date, Long id, ApplicationStatus status, String resumeUrl, String coverLetterUrl, long jobOfferId, String interviewResponse, String applicationResponse, String userEmail ) {
        this.date = date;
        this.id = id;
        this.status = status;
        this.resumeUrl = resumeUrl;
        this.coverLetterUrl = coverLetterUrl;
        this.jobOfferId = jobOfferId;
        this.interviewResponse = interviewResponse;
        this.applicationResponse = applicationResponse;
        this.userEmail = userEmail;
    }

    private LocalDate date;

    @Enumerated (EnumType.STRING)
    private ApplicationStatus status;

    private String resumeUrl;

    private String coverLetterUrl;

    private String applicationResponse;

    private String interviewResponse;

    private long jobOfferId;

    private String userEmail;

    public Application () {
    }

    // Constructor with required fields
    public Application ( LocalDate date, ApplicationStatus status, String resumeUrl, String coverLetterUrl,
                         String applicationResponse, String interviewResponse, long jobOfferId, String userEmail ) {
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

    @Override
    public String toString () {
        return "Application{" +
                "id=" + id +
                ", date=" + date +
                ", status=" + status +
                ", resumeUrl='" + resumeUrl + '\'' +
                ", coverLetterUrl='" + coverLetterUrl + '\'' +
                ", applicationResponse='" + applicationResponse + '\'' +
                ", interviewResponse='" + interviewResponse + '\'' +
                ", jobOfferId=" + jobOfferId +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }

    public void accept () {
        this.status = ApplicationStatus.ACCEPTED;
        this.date = LocalDate.now();
    }

    public void reject () {
        this.status = ApplicationStatus.REJECTED;
        this.date = LocalDate.now();
    }
}
