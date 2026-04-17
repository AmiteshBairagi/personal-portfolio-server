package com.amitesh.personal_portfolio_server.controller;

import com.amitesh.personal_portfolio_server.dto.CertificationUpdateRequest;
import com.amitesh.personal_portfolio_server.model.Certification;
import com.amitesh.personal_portfolio_server.model.Project;
import com.amitesh.personal_portfolio_server.service.CertificationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class CertificationController {

    private final CertificationService certificationService;

    public CertificationController(CertificationService certificationService) {
        this.certificationService = certificationService;
    }



    @PostMapping(value = "/add-certification" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addCertification(@RequestPart("body") Certification certification,
                                              @RequestPart(value = "image" , required = true)MultipartFile image){
        return certificationService.addCertification(certification,image);
    }



    @GetMapping("/get-all-certifications")
    public ResponseEntity<?> getAllCertifications(){
        return certificationService.getAllCertifications();
    }



    @DeleteMapping("/delete-certification/{certificationId}")
    public ResponseEntity<?> deleteCertification(@PathVariable String certificationId){
        return certificationService.deleteCertification(certificationId);
    }


    @PutMapping(value = "/update-certification/{certId}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateCertification(@RequestPart("data")CertificationUpdateRequest certificationUpdateRequest,
                                                 @RequestPart(value = "image" , required = false) MultipartFile image,
                                                 @PathVariable String certId){

        return certificationService.updateCertification(certificationUpdateRequest,image,certId);

    }


}
