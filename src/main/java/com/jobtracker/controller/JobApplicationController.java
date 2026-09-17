package com.jobtracker.controller;

import com.jobtracker.dto.JobApplicationRequest;
import com.jobtracker.dto.JobApplicationResponse;
import com.jobtracker.service.JobApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    private final JobApplicationService service;

    public JobApplicationController(JobApplicationService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<JobApplicationResponse> createApplication(
            @RequestBody JobApplicationRequest request) {

        return ResponseEntity.ok(
                service.createApplication(request)
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<JobApplicationResponse>> getAllApplications() {

        return ResponseEntity.ok(
                service.getAllApplications()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationResponse> getApplicationById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getApplicationById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<JobApplicationResponse> updateApplication(
            @PathVariable Long id,
            @RequestBody JobApplicationRequest request) {

        return ResponseEntity.ok(
                service.updateApplication(id, request)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(
            @PathVariable Long id) {

        service.deleteApplication(id);

        return ResponseEntity.noContent().build();
    }
}