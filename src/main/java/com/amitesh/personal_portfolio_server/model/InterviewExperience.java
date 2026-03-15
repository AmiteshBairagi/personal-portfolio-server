package com.amitesh.personal_portfolio_server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "interview-experiences")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterviewExperience {

    @Id
    private String id;
    @NonNull
    private String companyName;
    @NonNull
    private String jobProfile;
    @NonNull
    private LocalDate interviewDate;
    @NonNull
    private Integer rounds;
    @NonNull
    private Integer qualifiedRounds;
    @NonNull
    private String finalResult;
    @NonNull
    private Boolean featured;
    @NonNull
    private String description;
    @NonNull
    private String excerpt;

    private LocalDateTime createdAt;
}
