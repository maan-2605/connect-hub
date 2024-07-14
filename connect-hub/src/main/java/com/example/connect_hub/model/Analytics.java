package com.example.connect_hub.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "analytics")
public class Analytics {
    @Id
    private String id;
    private String userId;
    private String linkId; // null for page views
    private LocalDateTime timestamp;
    private String ipAddress;
    private String userAgent;
    private String country; // New field for geographic data
    private String city; // New field for geographic data
}