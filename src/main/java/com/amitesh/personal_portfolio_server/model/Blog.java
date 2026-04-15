package com.amitesh.personal_portfolio_server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "blogs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    @Id
    private String id;

    @NonNull
    private String title;

    @Indexed(unique = true)
    @NonNull
    private String slug;

    @NonNull
    private String author;

    @Indexed
    @NonNull
    private String category;

    @NonNull
    private String content;

    @NonNull
    private String excerpt;

    @NonNull
    private List<String> tags;

    @NonNull
    private Boolean featured = true;

    @NonNull
    private Boolean published = true;


    private String imageUrl;

    private String imagePublicId;

    // Date only (as per your payload)

    private LocalDateTime publishedAt;

    @NonNull
    private Integer readTime;

    @Indexed
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}