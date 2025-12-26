package com.secureaudit.secureaudit.service;

import com.secureaudit.secureaudit.domain.AuditLog;
import com.secureaudit.secureaudit.dto.log.LogIngestRequest;
import com.secureaudit.secureaudit.repository.AuditLogRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class LogService {

    private final AuditLogRepository repository;
    private final KmsEncryptionService kms;

    public LogService(AuditLogRepository repository, KmsEncryptionService kms) {
        this.repository = repository;
        this.kms = kms;
    }

    public void ingest(LogIngestRequest request) {

        String encrypted = kms.encrypt(request.getMessage());

        AuditLog log = new AuditLog();
        log.setTimestamp(request.getTimestamp());
        log.setServiceName(request.getServiceName());
        log.setSeverity(request.getSeverity());
        log.setEncryptedMessage(encrypted); // invariant: only ciphertext stored
        log.setSourceIp(request.getSourceIp());
        log.setCreatedAt(Instant.now());

        repository.save(log);
    }
}
