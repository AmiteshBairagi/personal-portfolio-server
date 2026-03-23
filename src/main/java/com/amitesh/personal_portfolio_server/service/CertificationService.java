package com.amitesh.personal_portfolio_server.service;

import com.amitesh.personal_portfolio_server.model.Certification;
import com.amitesh.personal_portfolio_server.model.Project;
import com.amitesh.personal_portfolio_server.repo.CertificationRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CertificationService {

    private final CertificationRepo certificationRepo;

    public CertificationService(CertificationRepo certificationRepo) {
        this.certificationRepo = certificationRepo;
    }

    @Transactional
    public ResponseEntity<?> addCertification(Certification certification) {
        try{
            certification.setCreatedAt(LocalDateTime.now());
            certification.setUpdatedAt(LocalDateTime.now());
            certificationRepo.save(certification);

            return ResponseEntity.status(200).body("certification Saved Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("certification cannot be saved , something went wrong");
        }
    }

    public ResponseEntity<?> getAllCertifications() {
        try{
            List<Certification> certifications = new ArrayList<>();
            certifications = certificationRepo.findAll();

            return ResponseEntity.status(200).body(certifications);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Cannot get certifications , something went wrong");
        }
    }

    @Transactional
    public ResponseEntity<?> deleteCertification(String certificationId) {
        try{
            certificationRepo.deleteById(certificationId);
            return ResponseEntity.status(200).body("certification deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("certification cannot be deleted , something went wrong");
        }
    }


//    public ResponseEntity<?> updateCertification(Certification certification) {
//    }
}
