package com.jobtracker.dto;

import java.time.LocalDate;

public class JobApplicationResponse {

    private Long id;
    private String companyName;
    private String jobTitle;
    private String jobUrl;
    private String location;
    private String status;
    private LocalDate applicationDate;
    private String notes;

    public JobApplicationResponse(
            Long id,
            String companyName,
            String jobTitle,
            String jobUrl,
            String location,
            String status,
            LocalDate applicationDate,
            String notes) {

        this.id = id;
        this.companyName = companyName;
        this.jobTitle = jobTitle;
        this.jobUrl = jobUrl;
        this.location = location;
        this.status = status;
        this.applicationDate = applicationDate;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobUrl() {
        return jobUrl;
    }

    public String getLocation() {
        return location;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    public String getNotes() {
        return notes;
    }
}