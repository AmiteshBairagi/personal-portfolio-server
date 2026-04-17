package com.amitesh.personal_portfolio_server.service;

import com.amitesh.personal_portfolio_server.dto.CertificationUpdateRequest;
import com.amitesh.personal_portfolio_server.model.Certification;
import com.amitesh.personal_portfolio_server.repo.CertificationRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CertificationService {

    private final CertificationRepo certificationRepo;
    private final CloudinaryService cloudinaryService;

    public CertificationService(CertificationRepo certificationRepo, CloudinaryService cloudinaryService) {
        this.certificationRepo = certificationRepo;
        this.cloudinaryService = cloudinaryService;
    }

    @Transactional
    public ResponseEntity<?> addCertification(Certification certification, MultipartFile image) {
        try{
            Map cloudinaryResponse = cloudinaryService.upload(image);

            String imageUrl = cloudinaryResponse.get("secure_url").toString();
            String imagePublicId = cloudinaryResponse.get("public_id").toString();

            certification.setImageUrl(imageUrl);
            certification.setImagePublicId(imagePublicId);

            certificationRepo.save(certification);

            return ResponseEntity.status(200).body("Certification saved successfully");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Certification cannot be added");
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

    @Transactional
    public ResponseEntity<?> updateCertification(CertificationUpdateRequest certificationUpdateRequest, MultipartFile image, String certId) {
        try{
            Certification certification = certificationRepo.findById(certId).orElseThrow(() -> new RuntimeException("No certification found to update"));

            if(image != null && !image.isEmpty()){
                Map cloudinaryResponse = cloudinaryService.update(image,certification.getImagePublicId());

                String imageUrl = cloudinaryResponse.get("secure_url").toString();
                String imagePublicId = cloudinaryResponse.get("public_id").toString();

                certification.setImagePublicId(imagePublicId);
                certification.setImageUrl(imageUrl);
            }

            if(certificationUpdateRequest.getTitle() != null){
                certification.setTitle(certificationUpdateRequest.getTitle());
            }
            if(certificationUpdateRequest.getIssuer() != null){
                certification.setIssuer(certificationUpdateRequest.getIssuer());
            }
            if(certificationUpdateRequest.getDate() != null){
                certification.setDate(certificationUpdateRequest.getDate());
            }
            if(certificationUpdateRequest.getCredentialId() != null){
                certification.setCredentialId(certificationUpdateRequest.getCredentialId());
            }
            if(certificationUpdateRequest.getDescription() != null){
                certification.setDescription(certificationUpdateRequest.getDescription());
            }
            if(certificationUpdateRequest.getSkills() != null){
                certification.setSkills(certificationUpdateRequest.getSkills());
            }
            if(certificationUpdateRequest.getVerificationUrl() != null){
                certification.setVerificationUrl(certificationUpdateRequest.getVerificationUrl());
            }
            if(certificationUpdateRequest.getFeatured() != null){
                certification.setFeatured(certificationUpdateRequest.getFeatured());
            }
            if(certificationUpdateRequest.getValidUntil() != null){
                certification.setValidUntil(certificationUpdateRequest.getValidUntil());
            }
            if(certificationUpdateRequest.getLevel() != null){
                certification.setLevel(certificationUpdateRequest.getLevel());
            }
            if(certificationUpdateRequest.getExamScore() != null){
                certification.setExamScore(certificationUpdateRequest.getExamScore());
            }
            if(certificationUpdateRequest.getDisplayOrder() != null){
                certification.setDisplayOrder(certificationUpdateRequest.getDisplayOrder());
            }

            certification.setUpdatedAt(LocalDateTime.now());

            certificationRepo.save(certification);

            return ResponseEntity.status(200).body("Certification updated successfully");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Something went wrong , certification cannot be updated");
        }
    }
}
