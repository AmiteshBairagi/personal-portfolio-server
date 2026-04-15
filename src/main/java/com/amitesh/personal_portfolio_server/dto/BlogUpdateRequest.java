package com.amitesh.personal_portfolio_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogUpdateRequest {

    private String title;
    private String slug;
    private String excerpt;
    private String content;
    private String author;
    private List<String> tags;
    private String category;
    private Boolean featured;
    private Boolean published;
}
