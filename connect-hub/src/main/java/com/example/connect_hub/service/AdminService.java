package com.example.connect_hub.service;

import com.example.connect_hub.model.User;
import com.example.connect_hub.repository.UserRepository;
import com.example.connect_hub.repository.LinkRepository;
import com.example.connect_hub.repository.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private AnalyticsRepository analyticsRepository;

    public long getTotalUsers() {
        return userRepository.count();
    }

    public long getTotalLinks() {
        return linkRepository.count();
    }

    public long getTotalPageViews() {
        return analyticsRepository.countByLinkIdIsNull();
    }

    public long getTotalLinkClicks() {
        return analyticsRepository.countByLinkIdIsNotNull();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
        linkRepository.deleteByUserId(userId);
        analyticsRepository.deleteByUserId(userId);
    }
}