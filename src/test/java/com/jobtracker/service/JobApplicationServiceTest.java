package com.jobtracker.service;

import com.jobtracker.dto.JobApplicationRequest;
import com.jobtracker.dto.JobApplicationResponse;
import com.jobtracker.entity.JobApplication;
import com.jobtracker.repository.JobApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobApplicationServiceTest {

    @Mock
    private JobApplicationRepository repository;

    @InjectMocks
    private JobApplicationService service;

    @Test
    void shouldCreateApplication() {

        JobApplicationRequest request = new JobApplicationRequest();

        request.setCompanyName("Amazon");
        request.setJobTitle("Java Backend Developer");
        request.setJobUrl("https://amazon.jobs");
        request.setLocation("Bangalore");
        request.setStatus("APPLIED");
        request.setApplicationDate(LocalDate.of(2026, 9, 17));
        request.setNotes("Applied through careers portal");

        JobApplication savedApplication = new JobApplication();

        savedApplication.setId(1L);
        savedApplication.setCompanyName("Amazon");
        savedApplication.setJobTitle("Java Backend Developer");
        savedApplication.setJobUrl("https://amazon.jobs");
        savedApplication.setLocation("Bangalore");
        savedApplication.setStatus("APPLIED");
        savedApplication.setApplicationDate(
                LocalDate.of(2026, 9, 17)
        );
        savedApplication.setNotes("Applied through careers portal");

        when(repository.save(any(JobApplication.class)))
                .thenReturn(savedApplication);

        JobApplicationResponse response =
                service.createApplication(request);

        assertEquals(1L, response.getId());
        assertEquals("Amazon", response.getCompanyName());
        assertEquals(
                "Java Backend Developer",
                response.getJobTitle()
        );
        assertEquals("APPLIED", response.getStatus());

        verify(repository, times(1))
                .save(any(JobApplication.class));
    }
}