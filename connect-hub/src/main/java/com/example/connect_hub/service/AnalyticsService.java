package com.example.connect_hub.service;

import com.example.connect_hub.model.Analytics;
import com.example.connect_hub.repository.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {


    @Autowired
    private AnalyticsRepository analyticsRepository;


    public void recordLinkClick(String userId, String linkId, String ipAddress, String userAgent) {
        Analytics analytics = new Analytics();
        analytics.setUserId(userId);
        analytics.setLinkId(linkId);
        analytics.setTimestamp(LocalDateTime.now());
        analytics.setIpAddress(ipAddress);
        analytics.setUserAgent(userAgent);
        analyticsRepository.save(analytics);
    }

    public long getPageViews(String userId) {
        return analyticsRepository.countByUserIdAndLinkIdIsNull(userId);
    }

    public long getLinkClicks(String userId, String linkId) {
        return analyticsRepository.countByUserIdAndLinkId(userId, linkId);
    }


    @Autowired
    private GeoLocationService geoLocationService;

    // ... existing methods ...

    public void recordPageView(String userId, String ipAddress, String userAgent) {
        Analytics analytics = new Analytics();
        analytics.setUserId(userId);
        analytics.setTimestamp(LocalDateTime.now());
        analytics.setIpAddress(ipAddress);
        analytics.setUserAgent(userAgent);
        analytics.setCountry(geoLocationService.getCountry(ipAddress));
        analytics.setCity(geoLocationService.getCity(ipAddress));
        analyticsRepository.save(analytics);
    }

    public Map<LocalDateTime, Long> getDailyViewsLast30Days(String userId) {
        LocalDateTime end = LocalDateTime.now();
        LocalDateTime start = end.minusDays(30);
        List<Analytics> analytics = analyticsRepository.findDailyAnalytics(userId, start, end);

        return analytics.stream()
                .collect(Collectors.groupingBy(a -> a.getTimestamp().toLocalDate().atStartOfDay(), Collectors.counting()));
    }

    public Map<String, Long> getViewsByCountry(String userId) {
        List<Analytics> analytics = analyticsRepository.findByUserId(userId);

        return analytics.stream()
                .collect(Collectors.groupingBy(Analytics::getCountry, Collectors.counting()));
    }



}
