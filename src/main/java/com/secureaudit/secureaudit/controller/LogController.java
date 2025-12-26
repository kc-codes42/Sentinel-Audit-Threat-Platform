package com.secureaudit.secureaudit.controller;

import com.secureaudit.secureaudit.dto.log.LogIngestRequest;
import com.secureaudit.secureaudit.service.LogService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logs")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','ANALYST')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void ingest(@Valid @RequestBody LogIngestRequest request) {
        logService.ingest(request);
    }
}
