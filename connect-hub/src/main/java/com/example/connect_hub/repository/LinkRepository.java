package com.example.connect_hub.repository;

import com.example.connect_hub.model.Link;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkRepository extends MongoRepository<Link, String> {
    List<Link> findByUserId(String userId);
    void deleteByUserId(String userId);
}