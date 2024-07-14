package com.example.connect_hub.repository;


import com.example.connect_hub.model.Analytics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AnalyticsRepository extends MongoRepository<Analytics, String> {
    long countByUserIdAndLinkId(String userId, String linkId);
    long countByUserIdAndLinkIdIsNull(String userId);
    long countByLinkIdIsNull();
    long countByLinkIdIsNotNull();
    void deleteByUserId(String userId);
    List<Analytics> findByUserIdAndTimestampBetween(String userId, LocalDateTime start, LocalDateTime end);

    @Query("{'userId': ?0, 'timestamp': {$gte: ?1, $lt: ?2}}")
    List<Analytics> findDailyAnalytics(String userId, LocalDateTime start, LocalDateTime end);

    @Query("{'userId': ?0, 'country': ?1}")
    List<Analytics> findByUserIdAndCountry(String userId, String country);

    List<Analytics> findByUserId(String userId);
}