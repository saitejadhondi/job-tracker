package com.jobtracker.service;

import com.jobtracker.dto.JobApplicationRequest;
import com.jobtracker.dto.JobApplicationResponse;
import com.jobtracker.entity.JobApplication;
import com.jobtracker.repository.JobApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationService {

    private final JobApplicationRepository repository;

    public JobApplicationService(JobApplicationRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public JobApplicationResponse createApplication(
            JobApplicationRequest request) {

        JobApplication application = new JobApplication();

        application.setCompanyName(request.getCompanyName());
        application.setJobTitle(request.getJobTitle());
        application.setJobUrl(request.getJobUrl());
        application.setLocation(request.getLocation());
        application.setStatus(request.getStatus());
        application.setApplicationDate(request.getApplicationDate());
        application.setNotes(request.getNotes());

        JobApplication savedApplication =
                repository.save(application);

        return convertToResponse(savedApplication);
    }

    // GET ALL
    public List<JobApplicationResponse> getAllApplications() {

        return repository.findAll()
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    // GET BY ID
    public JobApplicationResponse getApplicationById(Long id) {

        JobApplication application = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Job application not found with id: " + id));

        return convertToResponse(application);
    }

    // UPDATE
    public JobApplicationResponse updateApplication(
            Long id,
            JobApplicationRequest request) {

        JobApplication application = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Job application not found with id: " + id));

        application.setCompanyName(request.getCompanyName());
        application.setJobTitle(request.getJobTitle());
        application.setJobUrl(request.getJobUrl());
        application.setLocation(request.getLocation());
        application.setStatus(request.getStatus());
        application.setApplicationDate(request.getApplicationDate());
        application.setNotes(request.getNotes());

        JobApplication updatedApplication =
                repository.save(application);

        return convertToResponse(updatedApplication);
    }

    // DELETE
    public void deleteApplication(Long id) {

        JobApplication application = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Job application not found with id: " + id));

        repository.delete(application);
    }
    public List<JobApplicationResponse> searchByCompany(String companyName) {
    return repository.findByCompanyNameContainingIgnoreCase(companyName)
            .stream()
            .map(this::convertToResponse)
            .toList();
}

public List<JobApplicationResponse> searchByStatus(String status) {
    return repository.findByStatusIgnoreCase(status)
            .stream()
            .map(this::convertToResponse)
            .toList();
}
    // ENTITY → RESPONSE DTO
    private JobApplicationResponse convertToResponse(
            JobApplication application) {

        return new JobApplicationResponse(
                application.getId(),
                application.getCompanyName(),
                application.getJobTitle(),
                application.getJobUrl(),
                application.getLocation(),
                application.getStatus(),
                application.getApplicationDate(),
                application.getNotes()
        );
    }
}