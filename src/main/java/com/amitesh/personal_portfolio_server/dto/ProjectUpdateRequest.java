package com.amitesh.personal_portfolio_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUpdateRequest {

    private String title;

    private String description;

    private String shortDescription;

    private List<String> technologies;

    private String githubUrl;

    private String liveUrl;

    private String category;

    private Boolean featured;

    private String duration;

    private String teamSize;

    private String solution;

    private String challenges;

    private List<String> features;
}
