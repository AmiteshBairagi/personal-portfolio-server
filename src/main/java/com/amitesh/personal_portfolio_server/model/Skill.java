package com.amitesh.personal_portfolio_server.model;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "skills")
public class Skill {

    @Id
    private String id;

    @NonNull
    private String name;


    // equivalent to text[] in postgres
    @NonNull
    private List<String> projects;
    @NonNull
    private String yearsOfExperience;

    @Indexed
    @NonNull
    private String category;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}