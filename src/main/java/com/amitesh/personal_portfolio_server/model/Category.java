package com.amitesh.personal_portfolio_server.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    private String id;

    private String name;

    private String description;

    private String color = "#8B5CF6";

    private String icon = "🚀";

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
