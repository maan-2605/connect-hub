package com.example.connect_hub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LinkClickData {
    private String linkTitle;
    private long clicks;
}