package com.example.connect_hub.repository;


import com.example.connect_hub.model.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepository extends MongoRepository<Admin, String> {
    boolean existsByUserId(String userId);
}
