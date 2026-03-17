package com.amitesh.personal_portfolio_server.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "projects")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    private String id;

    @NonNull
    private String title;

    @NonNull
    private String description;

    @NonNull
    private String shortDescription;

    private List<String> technologies;

    private String githubUrl;

    private String liveUrl;

    @Indexed
    @NonNull
    private String category;

    @Indexed
    private Boolean featured = false;

    private String duration;

    private String teamSize;

    private String image;

    private String problemStatement;

    private String solution;

    private String challenges;

    private List<String> features;

    @Indexed
    private Boolean published = true;

    @Indexed
    private Integer displayOrder = 0;

    @Indexed
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}