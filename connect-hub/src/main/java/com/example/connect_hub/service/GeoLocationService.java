package com.example.connect_hub.service;


import org.springframework.stereotype.Service;

@Service
public class GeoLocationService {
    public String getCountry(String ipAddress) {
        // In a real implementation, this would use a geolocation API or database
        return "United States";
    }

    public String getCity(String ipAddress) {
        // In a real implementation, this would use a geolocation API or database
        return "New York";
    }
}