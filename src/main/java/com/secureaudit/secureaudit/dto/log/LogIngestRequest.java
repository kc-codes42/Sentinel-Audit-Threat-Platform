package com.secureaudit.secureaudit.dto.log;

import com.secureaudit.secureaudit.domain.enums.Severity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public class LogIngestRequest {

    @NotNull
    private Instant timestamp;

    @NotBlank
    private String serviceName;

    @NotNull
    private Severity severity;

    @NotBlank
    private String message; // plaintext only, never stored

    @NotBlank
    private String sourceIp;

    // getters and setters
    public Instant getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public Severity getSeverity() {
        return severity;
    }
    public void setSeverity(Severity severity) {
        this.severity = severity;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getSourceIp() {
        return sourceIp;
    }
    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }
}
