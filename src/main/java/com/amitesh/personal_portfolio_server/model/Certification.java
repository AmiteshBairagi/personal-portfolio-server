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

    @NonNull
    private String title;

    @NonNull
    private String issuer;

    @NonNull
    private String date;

    @NonNull
    private String credentialId;

    @NonNull
    private String imageUrl;

    private String imagePublicId;

    private String description;

    @NonNull
    private List<String> skills;

    @NonNull
    private String verificationUrl;

    private Boolean featured = false;

    @NonNull
    private String validUntil;

    private String level;

    private String examScore;

    private Integer displayOrder = 0;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}