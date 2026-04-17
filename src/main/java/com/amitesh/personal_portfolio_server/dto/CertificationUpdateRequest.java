package com.amitesh.personal_portfolio_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificationUpdateRequest {

    private String title;

    private String issuer;

    private String date;

    private String credentialId;

    private String description;

    private List<String> skills;

    private String verificationUrl;

    private Boolean featured;

    private String validUntil;

    private String level;

    private String examScore;

    private Integer displayOrder;

}
