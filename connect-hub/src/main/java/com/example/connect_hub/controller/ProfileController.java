package com.example.connect_hub.controller;

import com.example.connect_hub.dto.LinkClickData;
import com.example.connect_hub.model.Link;
import com.example.connect_hub.model.User;
import com.example.connect_hub.service.AnalyticsService;
import com.example.connect_hub.service.FileStorageService;
import com.example.connect_hub.service.LinkService;
import com.example.connect_hub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private UserService userService;

    @Autowired
    private LinkService linkService;

    @GetMapping
    public String showProfile(Model model, Authentication authentication) {
        if (authentication == null || authentication.getName().equals("anonymousUser")) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }
        String username = authentication.getName();
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        model.addAttribute("links", linkService.getLinksByUserId(user.getId()));
        model.addAttribute("newLink", new Link());
        return "profile";
    }

    @PostMapping("/update")
    public String updateProfile(@ModelAttribute User updatedUser, Authentication authentication) {

        if (authentication == null || authentication.getName().equals("anonymousUser")) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }
        String username = authentication.getName();
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setBio(updatedUser.getBio());
        userService.updateUser(user);
        return "redirect:/profile";
    }

    @PostMapping("/links/add")
    public String addLink(@ModelAttribute Link newLink, Authentication authentication) {

        if (authentication == null || authentication.getName().equals("anonymousUser")) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }
        String username = authentication.getName();
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        newLink.setUserId(user.getId());
        linkService.createLink(newLink);
        return "redirect:/profile";
    }

    @PostMapping("/links/delete/{linkId}")
    public String deleteLink(@PathVariable String linkId) {

        linkService.deleteLink(linkId);
        return "redirect:/profile";
    }
    @PostMapping("/customize")
    public String updateCustomization(User updatedUser, Authentication authentication) {

        if (authentication == null || authentication.getName().equals("anonymousUser")) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }
        String username = authentication.getName();
        userService.updateCustomization(username, updatedUser);
        return "redirect:/profile";
    }
    @PostMapping("/upload-picture")
    public String uploadProfilePicture(@RequestParam("file") MultipartFile file, Authentication authentication) {

        if (authentication == null || authentication.getName().equals("anonymousUser")) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }
        try {
            String fileName = fileStorageService.storeFile(file);
            String profilePictureUrl = "/uploads/" + fileName;
            userService.updateProfilePicture(authentication.getName(), profilePictureUrl);
            return "redirect:/profile";
        } catch (IOException e) {
            // Handle exception
            return "redirect:/profile?error=upload";
        }

    }
    @Autowired
    private AnalyticsService analyticsService;

    // ... existing methods ...

    @GetMapping("/analytics")
    public String showAnalytics(Model model, Authentication authentication) {

        if (authentication == null || authentication.getName().equals("anonymousUser")) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }
        String username = authentication.getName();
        User user = userService.getUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        long pageViews = analyticsService.getPageViews(user.getId());
        model.addAttribute("pageViews", pageViews);

        List<Link> links = linkService.getLinksByUserId(user.getId());
        List<LinkClickData> linkClicksData = new ArrayList<>();
        for (Link link : links) {
            long clicks = analyticsService.getLinkClicks(user.getId(), link.getId());
            linkClicksData.add(new LinkClickData(link.getTitle(), clicks));
        }
        model.addAttribute("linkClicksData", linkClicksData);

        return "analytics";
    }
    @GetMapping("/detailed-analytics")
    public String showDetailedAnalytics(Model model, Authentication authentication) {

        if (authentication == null || authentication.getName().equals("anonymousUser")) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }
        User user = userService.getUserByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        String userId = user.getId();

        model.addAttribute("dailyViews", analyticsService.getDailyViewsLast30Days(userId));
        model.addAttribute("viewsByCountry", analyticsService.getViewsByCountry(userId));

        return "detailed-analytics";
    }
}