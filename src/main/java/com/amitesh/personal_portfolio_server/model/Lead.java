package com.amitesh.personal_portfolio_server.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "leads")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lead {

    @Id
    private String id;

    @NonNull
    private String name;

    @NonNull
    private String email;

//    @NonNull
    private String phone;

    @NonNull
    private String message;

    private LocalDateTime createdAt;


}
