package com.example.connect_hub.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "links")
public class Link {
    @Id
    private String id;
    private String userId;
    private String title;
    private String url;
    private int clicks;
    private int shares;
}