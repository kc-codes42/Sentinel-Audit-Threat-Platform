package com.secureaudit.secureaudit.service;

import com.google.cloud.kms.v1.CryptoKeyName;
import com.google.cloud.kms.v1.DecryptResponse;
import com.google.cloud.kms.v1.EncryptResponse;
import com.google.cloud.kms.v1.KeyManagementServiceClient;
import com.google.protobuf.ByteString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PreDestroy;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;



@Service
public class KmsEncryptionService {

    public String encrypt(String plaintext) {
        // Mock KMS for local testing - returns different Base64 each time
        byte[] fakeCiphertext = new byte[32];
        new Random().nextBytes(fakeCiphertext);
        return Base64.getEncoder().encodeToString(fakeCiphertext);
    }

    public String decrypt(String encrypted) {
        // Mock decrypt - returns "DECRYPTED: " + original
        return "DECRYPTED: " + encrypted;
    }
}

/* 
@Service
public class KmsEncryptionService {

    private final KeyManagementServiceClient kmsClient;
    private final CryptoKeyName keyName;

    public KmsEncryptionService(
            @Value("${gcp.project-id}") String projectId,
            @Value("${gcp.kms.location}") String location,
            @Value("${gcp.kms.key-ring}") String keyRing,
            @Value("${gcp.kms.key-name}") String keyName
    ) throws IOException {

        this.kmsClient = KeyManagementServiceClient.create(); // uses ADC/default creds
        this.keyName = CryptoKeyName.of(projectId, location, keyRing, keyName);
    }

    public String encrypt(String plaintext) {
        ByteString data = ByteString.copyFromUtf8(plaintext);
        EncryptResponse response = kmsClient.encrypt(this.keyName, data);
        return Base64.getEncoder().encodeToString(response.getCiphertext().toByteArray());
    }

    public String decrypt(String encrypted) {
        ByteString cipher = ByteString.copyFrom(Base64.getDecoder().decode(encrypted));
        DecryptResponse response = kmsClient.decrypt(this.keyName, cipher);
        return response.getPlaintext().toStringUtf8();
    }

    @PreDestroy
    public void close() {
        kmsClient.close();
    }
}
*/