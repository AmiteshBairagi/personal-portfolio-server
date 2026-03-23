package com.amitesh.personal_portfolio_server.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "certifications")
public class Certification {

    @Id
    private String id;

    private String title;

    private String issuer;

    private String date;

    private String credentialId;

    private String imageUrl;

    private String description;

    private List<String> skills;

    private String verificationUrl;

    private Boolean featured = false;

    private String validUntil;

    private String level; // Could be enum (see below)

    private String examScore;

    private Integer displayOrder = 0;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}