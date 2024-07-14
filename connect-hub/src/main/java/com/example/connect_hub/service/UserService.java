package com.example.connect_hub.service;


import com.example.connect_hub.model.User;
import com.example.connect_hub.model.UserRole;
import com.example.connect_hub.repository.AdminRepository;
import com.example.connect_hub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User updateUser(User user) {
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }
    public User updateCustomization(String username, User updatedUser) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setBackgroundColor(updatedUser.getBackgroundColor());
        user.setButtonColor(updatedUser.getButtonColor());
        user.setTextColor(updatedUser.getTextColor());
        user.setFontFamily(updatedUser.getFontFamily());
        user.setLayout(updatedUser.getLayout());

        return userRepository.save(user);
    }
    public User updateProfilePicture(String username, String profilePictureUrl) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setProfilePictureUrl(profilePictureUrl);
        return userRepository.save(user);
    }
    @Autowired
    private AdminRepository adminRepository;

    public boolean isAdmin(String userId) {
        return adminRepository.existsByUserId(userId);
    }

// ... existing methods ...
    // new new new
}