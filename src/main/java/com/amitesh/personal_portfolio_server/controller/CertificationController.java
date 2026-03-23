package com.amitesh.personal_portfolio_server.controller;

import com.amitesh.personal_portfolio_server.model.Certification;
import com.amitesh.personal_portfolio_server.model.Project;
import com.amitesh.personal_portfolio_server.service.CertificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CertificationController {

    private final CertificationService certificationService;

    public CertificationController(CertificationService certificationService) {
        this.certificationService = certificationService;
    }

    @PostMapping("/add-certification")
    public ResponseEntity<?> addCertification(@RequestBody Certification certification){
        return certificationService.addCertification(certification);
    }

    @GetMapping("/get-all-certifications")
    public ResponseEntity<?> getAllCertifications(){
        return certificationService.getAllCertifications();
    }

    @DeleteMapping("/delete-certification/{certificationId}")
    public ResponseEntity<?> deleteCertification(@PathVariable String certificationId){
        return certificationService.deleteCertification(certificationId);
    }

//    @PutMapping("/update-certification")
//    public ResponseEntity<?> updateCertification(@RequestBody Certification certification){
//        return certificationService.updateCertification(certification);
//    }
}
